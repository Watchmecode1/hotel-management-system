package it.faggiorosso.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import it.faggiorosso.entity.Cliente;
import it.faggiorosso.entity.Cliente.Alloggiato;
import it.faggiorosso.entity.Documento;
import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.exception.GuiInputFieldValueException;
import it.faggiorosso.service.PrenotazioneService;


public class FileUtils {
	
	private static final Path SITRA_DIRECTORY_PATH = Paths.get("." + File.separatorChar + "sitra");
	private static final Path PDF_DIRECTORY_PATH = Paths.get("." + File.separatorChar + "pdf");
	private static final String SITRA_FILE_EXTENSION = ".txt";
	private static final String PDF_FILE_EXTENSION = ".pdf";
	/**
	 * Comune+SiglaProvincia come chiave
	 */
	public static final Map<String, Long> CODICI_COMUNI = getCodiciComuni();
	public static final Map<String, Long> CODICI_STATI = getCodiciStati();
	public static final Map<String, String> SIGLE_PROVINCE = getSigleProvince();
	
	public static DefaultComboBoxModel<String> getComuni() {
		DefaultComboBoxModel<String> comuniModel = new DefaultComboBoxModel<>();
		comuniModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		return comuniModel;
	}
	
	public static DefaultComboBoxModel<String> getStati() {
		DefaultComboBoxModel<String> statiModel = new DefaultComboBoxModel<>();
		statiModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		return statiModel;
	}
	
	public static DefaultComboBoxModel<String> getProvince() {
		DefaultComboBoxModel<String> provinceModel = new DefaultComboBoxModel<>();
		provinceModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/elencoProvinceItaliane.txt"))).lines()
				.map(line -> line.split("\t"))
				.map(array -> array[0].toUpperCase())
				.toList());
		return provinceModel;
	}
	
	public static DefaultComboBoxModel<String> getStatiAndComuni() {
		DefaultComboBoxModel<String> statiAndComuniModel = new DefaultComboBoxModel<>();
		statiAndComuniModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
				.map(line -> line.split(","))
				.map(array -> array[1])
				.toList());
		statiAndComuniModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		statiAndComuniModel.removeElement("ITALIA");
		return statiAndComuniModel;
	}
	
	public static void writePDFAndOpen(Prenotazione prenotazione) throws IOException {
		Path pdfFilePath = Paths.get(PDF_DIRECTORY_PATH.toString() + File.separatorChar + prenotazione.getCognome().toUpperCase() + prenotazione.getDataInizio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + PDF_FILE_EXTENSION);
		
		if(!Files.exists(PDF_DIRECTORY_PATH))
        	Files.createDirectory(PDF_DIRECTORY_PATH);
		if(!Files.exists(pdfFilePath))
				Files.createFile(pdfFilePath);
		
		try(PdfWriter pdfWriter = new PdfWriter(pdfFilePath.toFile());
				PdfDocument pdfDocument = new PdfDocument(pdfWriter);
				Document document = new Document(pdfDocument)) {
            document.add(new Paragraph(prenotazione.getPdf()));

            Desktop.getDesktop().open(pdfFilePath.toFile());
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void writeSitraAndOpen(LocalDate localDate) {
		PrenotazioneService prenotazioneService = new PrenotazioneService();
		List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioneByDataInizio(localDate);
		String sitra = prenotazioni.stream()
										.map(prenotazione -> sortClienti(prenotazione.getClienti()).stream()
																					.map(cliente -> getSitraLine(cliente, prenotazione))
																					.collect(Collectors.joining("")))
										.collect(Collectors.joining(""));

		sitra = Pattern.compile("\\r\\n$").matcher(sitra).replaceAll("");
		
		try {
			if(!Files.exists(SITRA_DIRECTORY_PATH))
					Files.createDirectory(SITRA_DIRECTORY_PATH);
			Path sitraFilePath = Paths.get(SITRA_DIRECTORY_PATH.toString() + File.separatorChar + localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + SITRA_FILE_EXTENSION);
			
			Files.writeString(sitraFilePath, sitra);
			Desktop.getDesktop().open(SITRA_DIRECTORY_PATH.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Cliente> sortClienti(Set<Cliente> clienti) {
		if(clienti.size() == 1) return new ArrayList<>(clienti);
		
		List<Cliente> sortedClienti = new ArrayList<>();
		for(Cliente cliente : clienti) {
			if(cliente.getAlloggiato() == Alloggiato.CAPO_FAMIGLIA || cliente.getAlloggiato() == Alloggiato.CAPO_GRUPPO) {
				sortedClienti.add(cliente);
				clienti.remove(cliente);
				break;
			}
		}
		sortedClienti.addAll(clienti);
		
		return sortedClienti;
	}
	
	private static String getSitraLine(Cliente cliente, Prenotazione prenotazione) {
		StringBuilder sb = new StringBuilder();
		String nomeCliente = cliente.getNome().toUpperCase();
		String cognomeCliente = cliente.getCognome().toUpperCase();
		String dataInizioPrenotazione = prenotazione.getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dataDiNascitaCliente = cliente.getDataDiNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		sb.append(cliente.getAlloggiato().getNumero())
			.append(dataInizioPrenotazione)
			.append(getDaysOfBooking(prenotazione))
			.append(padRightSpaces(cognomeCliente, 50))
			.append(padRightSpaces(nomeCliente, 30))
			.append(cliente.getSesso().getNumber())
			.append(dataDiNascitaCliente);
		
		if(cliente.getStatoDiNascita().equalsIgnoreCase("ITALIA")) {
			String siglaProvincia = SIGLE_PROVINCE.get(cliente.getProvinciaDiNascita().toUpperCase());
			Long codiceComune = CODICI_COMUNI.get(cliente.getComuneDiNascita().toUpperCase() + siglaProvincia);
			if(codiceComune == null) throw new GuiInputFieldValueException("IL COMUNE DEL CLIENTE: " + nomeCliente + " " + cognomeCliente + " NON APPARTIENE AD UNA PROVINCIA VALIDA");
			sb.append(codiceComune)
				.append(siglaProvincia);
		}
		else
			sb.append(" ".repeat(11));
		
		sb.append(CODICI_STATI.get(cliente.getStatoDiNascita().toUpperCase()))
			.append(CODICI_STATI.get(cliente.getCittadinanza().toUpperCase()));
		
		Alloggiato alloggiato = cliente.getAlloggiato();
		if(alloggiato == Alloggiato.CAPO_FAMIGLIA || alloggiato == Alloggiato.CAPO_GRUPPO || alloggiato == Alloggiato.OSPITE_SINGOLO) {
			Documento documento = cliente.getDocumento();
			sb.append(documento.getTipoDocumento().getCodice())
				.append(padRightSpaces(documento.getNumero(), 20));

			String luogoDiRilascioDocumento = documento.getLuogoDiRilascio().toUpperCase();
			if(CODICI_STATI.containsKey(luogoDiRilascioDocumento))
				sb.append(CODICI_STATI.get(luogoDiRilascioDocumento));
			else
				sb.append(CODICI_COMUNI.get(luogoDiRilascioDocumento + SIGLE_PROVINCE.get(documento.getProvinciaDiRilascio())));
		} else
			sb.append(" ".repeat(34));
		sb.append("\r\n");
		
		return sb.toString();
	}
	
	private static String padRightSpaces(String string, int length) {
		StringBuilder sb = new StringBuilder(string);
		while(sb.length() < length) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	private static String getDaysOfBooking(Prenotazione prenotazione) {
		String days = String.valueOf(ChronoUnit.DAYS.between(prenotazione.getDataInizio(), prenotazione.getDataFine()));
		return days.length() < 2 ? 0 + days : days;
	}
	
	private static Map<String, Long> getCodiciComuni() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
																.map(line -> line.split(","))
																.collect(Collectors.toMap(array -> array[1] + array[2], array -> Long.valueOf(array[0])));
	}
	
	private static Map<String, Long> getCodiciStati() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
																.map(line -> line.split(","))
																.collect(Collectors.toMap(array -> array[1], array -> Long.valueOf(array[0])));
	}
	
	private static Map<String, String> getSigleProvince() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/elencoProvinceItaliane.txt"))).lines()
																.map(line -> line.split("\t"))
																.collect(Collectors.toMap(array -> array[0].toUpperCase(), array -> array[1]));
	}
}