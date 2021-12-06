package it.faggiorosso;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.faggiorosso.entity.Cliente;
import it.faggiorosso.entity.Cliente.Alloggiato;
import it.faggiorosso.entity.Cliente.Sesso;
import it.faggiorosso.entity.Consumazione;
import it.faggiorosso.entity.Documento;
import it.faggiorosso.entity.Documento.Rilascio;
import it.faggiorosso.entity.Documento.TipoDocumento;
import it.faggiorosso.entity.Magazzino;
import it.faggiorosso.entity.Ordine;
import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.entity.Prenotazione.Fonte;
import it.faggiorosso.entity.Prenotazione.Pagato;
import it.faggiorosso.entity.Prenotazione.Pensione;
import it.faggiorosso.entity.Prodotto;
import it.faggiorosso.entity.TipoCamera;
import it.faggiorosso.entity.TipoCamera.Tipo;
import it.faggiorosso.entity.Camera;
import it.faggiorosso.entity.Camera.Balcone;
import it.faggiorosso.entity.Camera.Piano;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.service.ConsumazioneService;
import it.faggiorosso.service.DocumentoService;
import it.faggiorosso.service.MagazzinoService;
import it.faggiorosso.service.OrdineService;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.service.ProdottoService;
import it.faggiorosso.service.TipoCameraService;
import it.faggiorosso.service.CameraService;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class Test {
	
	public static void main(String[] args) {
		ClienteService clienteService = new ClienteService();
		DocumentoService documentoService = new DocumentoService();
		ProdottoService prodottoService = new ProdottoService();
		CameraService cameraService = new CameraService();
		ConsumazioneService consumazioneService = new ConsumazioneService();
		PrenotazioneService prenotazioneService = new PrenotazioneService();
		MagazzinoService magazzinoService = new MagazzinoService();
		OrdineService ordineService = new OrdineService();
		TipoCameraService tipoCameraService = new TipoCameraService();
		
		/*
		 * Cliente cliente = new Cliente("mario", "rossi", Sesso.MASCHIO,
		 * LocalDate.parse("1990-01-21"), "napoli", "napoli", "123456",
		 * "ciao@ciao.com"); Documento documento = new Documento(cliente, "1234",
		 * TipoDocumento.CARTA_IDENTITA, Rilascio.QUESTURA);
		 * clienteService.saveCliente(cliente);
		 * documentoService.saveDocumento(documento);
		 * 
		 * Cliente cliente2 = new Cliente("michela", "rossi", Sesso.FEMMINA,
		 * LocalDate.parse("2015-01-21"), "napoli", "napoli"); Documento documento2 =
		 * new Documento(cliente2, "1111", TipoDocumento.CARTA_IDENTITA,
		 * Rilascio.QUESTURA); clienteService.saveCliente(cliente2);
		 * documentoService.saveDocumento(documento2);
		 * 
		 * Camera camera = new Camera(100, Tipo.DOPPIA, Piano.PRIMO,
		 * Balcone.CON_BALCONE); cameraService.saveCamera(camera);
		 * 
		 * Camera camera2 = new Camera(101, Tipo.DOPPIA, Piano.PRIMO,
		 * Balcone.SENZA_BALCONE); cameraService.saveCamera(camera2);
		 * 
		 * Prodotto prodotto = new Prodotto("Coca-cola", BigDecimal.valueOf(1.50));
		 * prodottoService.saveProdotto(prodotto);
		 * 
		 * //LocalDate.parse("2021-12-12") Prenotazione prenotazione = new
		 * Prenotazione(LocalDate.now(), LocalDate.now().plusDays(2), 1,
		 * Pagato.NON_PAGATO, Pensione.PENSIONE_COMPLETA, new
		 * HashSet<Cliente>(Collections.singleton(cliente)), new
		 * HashSet<Camera>(Set.of(camera)));
		 * prenotazioneService.savePrenotazione(prenotazione);
		 * 
		 * Prenotazione prenotazione2 = new Prenotazione(LocalDate.now(),
		 * LocalDate.now().plusDays(2), 1, Pagato.NON_PAGATO,
		 * Pensione.PENSIONE_COMPLETA, new
		 * HashSet<Cliente>(Collections.singleton(cliente2)), new
		 * HashSet<Camera>(Set.of(camera, camera2)));
		 * prenotazioneService.savePrenotazione(prenotazione2);
		 * 
		 * Prenotazione prenotazione3 = new Prenotazione(LocalDate.now().minusDays(3),
		 * LocalDate.now(), 0, Pagato.NON_PAGATO, Pensione.MEZZA_PENSIONE, new
		 * HashSet<Cliente>(Collections.singleton(cliente)), new
		 * HashSet<Camera>(Set.of(camera)));
		 * prenotazioneService.savePrenotazione(prenotazione3);
		 * 
		 * Consumazione consumazione = new Consumazione(prenotazione, prodotto, 1);
		 * consumazioneService.saveConsumazione(consumazione);
		 * 
		 * Magazzino magazzino = new Magazzino(prodotto, 10);
		 * magazzinoService.saveMagazzino(magazzino);
		 * 
		 * Ordine ordine = new Ordine("A1L2M", "kikko", LocalDate.now(),
		 * LocalDate.now().plusDays(2), prodotto, BigDecimal.valueOf(100));
		 * ordineService.saveOrdine(ordine);
		 * 
		 * //System.out.println(documentoService.getDocumentoById("1234"));
		 * 
		 * cameraService.getAll().forEach(System.out::println);
		 * System.out.println(cameraService.getByNumero(100));
		 * 
		 * prodottoService.getAll().forEach(System.out::println);
		 * System.out.println(prodottoService.getByNome("Coca-cola"));
		 * 
		 * clienteService.getAll().forEach(System.out::println);
		 * System.out.println(clienteService.getById((long) 1));
		 * 
		 * 
		 * System.out.println(prenotazioneService.getCheckIn());
		 * System.out.println(prenotazioneService.getCheckOut());
		 * System.out.println(ordineService.getOrdiniInScadenza());
		 * System.out.println(prenotazioneService.getPrenotazioniByCognomeCliente(
		 * "Rossi")); System.out.println("-------------");
		 * System.out.println(prenotazioneService.getPrenotazioneByCamere(List.of(
		 * camera2)));
		 * System.out.println(prenotazioneService.getPrenotazioneByCamere(List.of(
		 * camera, camera2))); System.out.println("---------------");
		 * System.out.println(prenotazioneService.getPrenotazioneByDataInizio(LocalDate.
		 * now()).isEmpty());
		 * System.out.println(prenotazioneService.getPrenotazioneByDataFine(LocalDate.
		 * now()).isEmpty());
		 * System.out.println(clienteService.getClientiByCognome("Rossi").isEmpty());
		 * System.out.println(clienteService.getClienteByNumeroDocumento("1234"));
		 */
		
		//prodottoService.getByNome("coca-cola")
		//Cliente cliente1 = new Cliente("mario", "minuto", Sesso.MASCHIO, LocalDate.now(), "italia", "roma", "roma", "italia", Alloggiato.OSPITE_SINGOLO);
		//Cliente cliente2 = new Cliente(null, "minuto", Sesso.MASCHIO, LocalDate.now(), "italia", "roma", "roma", "italia", Alloggiato.OSPITE_SINGOLO);
		//clienteService.saveCliente(cliente1);
		//clienteService.saveCliente(cliente2);
//		TipoCamera tipoCamera1 = new TipoCamera(Tipo.SINGOLA, new BigDecimal(80));
//		TipoCamera tipoCamera2 = new TipoCamera(Tipo.DOPPIA, new BigDecimal(100));
//		TipoCamera tipoCamera3 = new TipoCamera(Tipo.TRIPLA, new BigDecimal(120));
//		TipoCamera tipoCamera4 = new TipoCamera(Tipo.QUADRUPLA, new BigDecimal(140));
//		TipoCamera tipoCamera5 = new TipoCamera(Tipo.QUINTUPLA, new BigDecimal(160));
//		tipoCameraService.saveTipoCamera(tipoCamera1);
//		tipoCameraService.saveTipoCamera(tipoCamera2);
//		tipoCameraService.saveTipoCamera(tipoCamera3);
//		tipoCameraService.saveTipoCamera(tipoCamera4);
//		tipoCameraService.saveTipoCamera(tipoCamera5);
		
		//Prenotazione prenotazione = new Prenotazione("bianchi", "test@gmail.com", "33340353909", LocalDate.now(), LocalDate.now(), 0, Pagato.NON_PAGATO, BigDecimal.ZERO, Pensione.PENSIONE_COMPLETA, Fonte.HOTEL, Set.of(cliente1), Set.of(cameraService.getByNumero(102)));
		//prenotazioneService.savePrenotazione(prenotazione);
		
//		Camera camera1 = new Camera(106, tipoCameraService.getById(Tipo.DOPPIA), Piano.PRIMO, Balcone.SENZA_BALCONE);
//		Camera camera2 = new Camera(102, tipoCameraService.getById(Tipo.DOPPIA), Piano.PRIMO, Balcone.SENZA_BALCONE);
//		Camera camera3 = new Camera(201, tipoCameraService.getById(Tipo.SINGOLA), Piano.SECONDO, Balcone.SENZA_BALCONE);
//		cameraService.saveCamera(camera1);
//		cameraService.saveCamera(camera2);
//		cameraService.saveCamera(camera3);
		
		TipoCamera t2 = new TipoCamera(Tipo.DOPPIA, BigDecimal.valueOf(50));
		TipoCamera t3 = new TipoCamera(Tipo.TRIPLA, BigDecimal.valueOf(70));
		TipoCamera t4 = new TipoCamera(Tipo.QUADRUPLA, BigDecimal.valueOf(80));
		TipoCamera t5 = new TipoCamera(Tipo.QUINTUPLA, BigDecimal.valueOf(100));
		tipoCameraService.saveTipoCamera(t2);
		tipoCameraService.saveTipoCamera(t3);
		tipoCameraService.saveTipoCamera(t4);
		tipoCameraService.saveTipoCamera(t5);
		Camera camera101 = new Camera(101,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera102 = new Camera(102,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera103 = new Camera(103,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera104 = new Camera(104,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera105 = new Camera(105,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera106 = new Camera(106,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera107 = new Camera(107,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera108 = new Camera(108,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera109 = new Camera(109,t4,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera110 = new Camera(110,t2,Piano.PRIMO,Balcone.SENZA_BALCONE);
		Camera camera201 = new Camera(201,t4,Piano.SECONDO,Balcone.SENZA_BALCONE);
		Camera camera202 = new Camera(202,t2,Piano.SECONDO,Balcone.SENZA_BALCONE);
		Camera camera203 = new Camera(203,t4,Piano.SECONDO,Balcone.SENZA_BALCONE);
		Camera camera204 = new Camera(204,t3,Piano.SECONDO,Balcone.SENZA_BALCONE);
		Camera camera205 = new Camera(205,t2,Piano.SECONDO,Balcone.SENZA_BALCONE);
		Camera camera206 = new Camera(206,t5,Piano.SECONDO,Balcone.CON_BALCONE);
		Camera camera207 = new Camera(207,t5,Piano.SECONDO,Balcone.CON_BALCONE);
		Camera camera208 = new Camera(208,t5,Piano.SECONDO,Balcone.CON_BALCONE);
		Camera camera209 = new Camera(209,t5,Piano.SECONDO,Balcone.CON_BALCONE);
		Camera camera301 = new Camera(301,t3,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera302 = new Camera(302,t3,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera303 = new Camera(303,t4,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera304 = new Camera(304,t4,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera305 = new Camera(305,t2,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera306 = new Camera(306,t2,Piano.TERZO,Balcone.SENZA_BALCONE);
		Camera camera401 = new Camera(401,t2,Piano.QUARTO,Balcone.SENZA_BALCONE);
		Camera camera402 = new Camera(402,t2,Piano.QUARTO,Balcone.SENZA_BALCONE);
		Camera camera403 = new Camera(403,t3,Piano.QUARTO,Balcone.SENZA_BALCONE);
		Camera camera404 = new Camera(404,t2,Piano.QUARTO,Balcone.SENZA_BALCONE);
		Camera camera405 = new Camera(405,t2,Piano.QUARTO,Balcone.SENZA_BALCONE);
		Camera camera1000 = new Camera(1000,t5,Piano.QUINTO,Balcone.SENZA_BALCONE);
		Camera camera1001 = new Camera(10001,t5,Piano.QUINTO,Balcone.SENZA_BALCONE);
		cameraService.saveCamera(camera101);
		cameraService.saveCamera(camera102);
		cameraService.saveCamera(camera103);
		cameraService.saveCamera(camera104);
		cameraService.saveCamera(camera105);
		cameraService.saveCamera(camera106);
		cameraService.saveCamera(camera107);
		cameraService.saveCamera(camera108);
		cameraService.saveCamera(camera109);
		cameraService.saveCamera(camera110);
		cameraService.saveCamera(camera201);
		cameraService.saveCamera(camera202);
		cameraService.saveCamera(camera203);
		cameraService.saveCamera(camera204);
		cameraService.saveCamera(camera205);
		cameraService.saveCamera(camera206);
		cameraService.saveCamera(camera207);
		cameraService.saveCamera(camera208);
		cameraService.saveCamera(camera209);
		cameraService.saveCamera(camera301);
		cameraService.saveCamera(camera302);
		cameraService.saveCamera(camera303);
		cameraService.saveCamera(camera304);
		cameraService.saveCamera(camera305);
		cameraService.saveCamera(camera306);
		cameraService.saveCamera(camera401);
		cameraService.saveCamera(camera402);
		cameraService.saveCamera(camera403);
		cameraService.saveCamera(camera404);
		cameraService.saveCamera(camera405);
		cameraService.saveCamera(camera1000);
		cameraService.saveCamera(camera1001);
	}
}