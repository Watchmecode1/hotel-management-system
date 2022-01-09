package com.hotel.util;

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

import com.hotel.entity.Customer;
import com.hotel.entity.Document;
import com.hotel.entity.Reservation;
import com.hotel.entity.Customer.Housed;
import com.hotel.exception.GuiInputFieldValueException;
import com.hotel.service.ReservationService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;


public class FileUtils {
	
	private static final Path SITRA_DIRECTORY_PATH = Paths.get("." + File.separatorChar + "sitra");
	private static final Path PDF_DIRECTORY_PATH = Paths.get("." + File.separatorChar + "pdf");
	private static final String SITRA_FILE_EXTENSION = ".txt";
	private static final String PDF_FILE_EXTENSION = ".pdf";
	/**
	 * Comune+SiglaProvincia as keys
	 */
	public static final Map<String, Long> MUNICIPAL_CODES = getMunicipalCodes();
	public static final Map<String, Long> STATE_CODES = getStateCodes();
	public static final Map<String, String> ABBREVIATIONS_OF_THE_PROVINCES = getAbbreviationsOfTheProvinces();
	
	public static DefaultComboBoxModel<String> getMunicipals() {
		DefaultComboBoxModel<String> municipalModel = new DefaultComboBoxModel<>();
		municipalModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		return municipalModel;
	}
	
	public static DefaultComboBoxModel<String> getStates() {
		DefaultComboBoxModel<String> statesModel = new DefaultComboBoxModel<>();
		statesModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		return statesModel;
	}
	
	public static DefaultComboBoxModel<String> getProvinces() {
		DefaultComboBoxModel<String> provincesModel = new DefaultComboBoxModel<>();
		provincesModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/elencoProvinceItaliane.txt"))).lines()
				.map(line -> line.split("\t"))
				.map(array -> array[0].toUpperCase())
				.toList());
		return provincesModel;
	}
	
	public static DefaultComboBoxModel<String> getStatesAndMunicipals() {
		DefaultComboBoxModel<String> statesAndMunicipalsModel = new DefaultComboBoxModel<>();
		statesAndMunicipalsModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
				.map(line -> line.split(","))
				.map(array -> array[1])
				.toList());
		statesAndMunicipalsModel.addAll(new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
					.map(line -> line.split(","))
					.map(array -> array[1])
					.toList());
		statesAndMunicipalsModel.removeElement("ITALIA");
		return statesAndMunicipalsModel;
	}
	
	public static void writePDFAndOpen(Reservation reservation) throws IOException {
		Path pdfFilePath = Paths.get(PDF_DIRECTORY_PATH.toString() + File.separatorChar + reservation.getSurname().toUpperCase() + reservation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + PDF_FILE_EXTENSION);
		
		if(!Files.exists(PDF_DIRECTORY_PATH))
        	Files.createDirectory(PDF_DIRECTORY_PATH);
		if(!Files.exists(pdfFilePath))
				Files.createFile(pdfFilePath);
		
		try(PdfWriter pdfWriter = new PdfWriter(pdfFilePath.toFile());
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument)) {
            document.add(new Paragraph(reservation.getPdf()));

            Desktop.getDesktop().open(pdfFilePath.toFile());
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void writeSitraAndOpen(LocalDate localDate) {
		ReservationService reservationService = new ReservationService();
		List<Reservation> prenotazioni = reservationService.getReservationByStartDate(localDate);
		String sitra = prenotazioni.stream()
										.map(prenotazione -> sortCustomers(prenotazione.getCustomers()).stream()
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
	
	private static List<Customer> sortCustomers(Set<Customer> customers) {
		if(customers.size() == 1) return new ArrayList<>(customers);
		
		List<Customer> sortedCustomers = new ArrayList<>();
		for(Customer customer : customers) {
			if(customer.getHoused() == Housed.HOUSEHOLDER || customer.getHoused() == Housed.GROUP_LEADER) {
				sortedCustomers.add(customer);
				customers.remove(customer);
				break;
			}
		}
		sortedCustomers.addAll(customers);
		
		return sortedCustomers;
	}
	
	private static String getSitraLine(Customer customer, Reservation reservation) {
		StringBuilder sb = new StringBuilder();
		String customerName = customer.getName().toUpperCase();
		String customerSurname = customer.getSurname().toUpperCase();
		String reservationStartDate = reservation.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String customerDateOfBirth = customer.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		sb.append(customer.getHoused().getNumber())
			.append(reservationStartDate)
			.append(getDaysOfBooking(reservation))
			.append(padRightSpaces(customerSurname, 50))
			.append(padRightSpaces(customerName, 30))
			.append(customer.getGender().getNumber())
			.append(customerDateOfBirth);
		
		if(customer.getStateOfBirth().equalsIgnoreCase("ITALIA")) {
			String abbreviationOfTheProvince = ABBREVIATIONS_OF_THE_PROVINCES.get(customer.getCountyOfBirth().toUpperCase());
			Long municipalCode = MUNICIPAL_CODES.get(customer.getBirthplace().toUpperCase() + abbreviationOfTheProvince);
			if(municipalCode == null) throw new GuiInputFieldValueException("IL COMUNE DEL CLIENTE: " + customerName + " " + customerSurname + " NON APPARTIENE AD UNA PROVINCIA VALIDA");
			sb.append(municipalCode)
				.append(abbreviationOfTheProvince);
		}
		else
			sb.append(" ".repeat(11));
		
		sb.append(STATE_CODES.get(customer.getStateOfBirth().toUpperCase()))
			.append(STATE_CODES.get(customer.getCitizenship().toUpperCase()));
		
		Housed housed = customer.getHoused();
		if(housed == Housed.HOUSEHOLDER || housed == Housed.GROUP_LEADER || housed == Housed.SINGLE_GUEST) {
			Document document = customer.getDocument();
			sb.append(document.getDocumentType().getCode())
				.append(padRightSpaces(document.getNumber(), 20));

			String documentPlaceOfIssue = document.getPlaceOfIssue().toUpperCase();
			if(STATE_CODES.containsKey(documentPlaceOfIssue))
				sb.append(STATE_CODES.get(documentPlaceOfIssue));
			else
				sb.append(MUNICIPAL_CODES.get(documentPlaceOfIssue + ABBREVIATIONS_OF_THE_PROVINCES.get(document.getProvinceOfIssue())));
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
	
	private static String getDaysOfBooking(Reservation reservation) {
		String days = String.valueOf(ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate()));
		return days.length() < 2 ? 0 + days : days;
	}
	
	private static Map<String, Long> getMunicipalCodes() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/comuni.txt"))).lines()
																.map(line -> line.split(","))
																.collect(Collectors.toMap(array -> array[1] + array[2], array -> Long.valueOf(array[0])));
	}
	
	private static Map<String, Long> getStateCodes() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/stati.txt"))).lines()
																.map(line -> line.split(","))
																.collect(Collectors.toMap(array -> array[1], array -> Long.valueOf(array[0])));
	}
	
	private static Map<String, String> getAbbreviationsOfTheProvinces() {
		return new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream("files/elencoProvinceItaliane.txt"))).lines()
																.map(line -> line.split("\t"))
																.collect(Collectors.toMap(array -> array[0].toUpperCase(), array -> array[1]));
	}
}