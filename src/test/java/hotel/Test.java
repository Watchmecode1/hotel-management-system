package hotel;

import com.hotel.entity.*;
import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.ConsumptionService;
import com.hotel.service.DocumentService;
import com.hotel.service.WarehouseService;
import com.hotel.service.OrderService;
import com.hotel.service.ReservationService;
import com.hotel.service.ProductService;
import com.hotel.service.RoomTypeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		DocumentService documentService = new DocumentService();
		ProductService productService = new ProductService();
		RoomService roomService = new RoomService();
		ConsumptionService consumptionService = new ConsumptionService();
		ReservationService reservationService = new ReservationService();
		WarehouseService warehouseService = new WarehouseService();
		OrderService orderService = new OrderService();
		RoomTypeService roomTypeService = new RoomTypeService();
		
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
		
		RoomType t2 = new RoomType(RoomType.Type.DOUBLE, BigDecimal.valueOf(50));
		RoomType t3 = new RoomType(RoomType.Type.TRIPLE, BigDecimal.valueOf(70));
		RoomType t4 = new RoomType(RoomType.Type.QUADRUPLE, BigDecimal.valueOf(80));
		RoomType t5 = new RoomType(RoomType.Type.QUINTUPLE, BigDecimal.valueOf(100));
		roomTypeService.saveRoomType(t2);
		roomTypeService.saveRoomType(t3);
		roomTypeService.saveRoomType(t4);
		roomTypeService.saveRoomType(t5);
		Room camera101 = new Room(101, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera102 = new Room(102, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera103 = new Room(103, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera104 = new Room(104, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera105 = new Room(105, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera106 = new Room(106, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera107 = new Room(107, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera108 = new Room(108, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera109 = new Room(109, t4, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera110 = new Room(110, t2, Room.Floor.FIRST, Room.Balcony.WITHOUT_BALCONY);
		Room camera201 = new Room(201, t4, Room.Floor.SECOND, Room.Balcony.WITHOUT_BALCONY);
		Room camera202 = new Room(202, t2, Room.Floor.SECOND, Room.Balcony.WITHOUT_BALCONY);
		Room camera203 = new Room(203, t4, Room.Floor.SECOND, Room.Balcony.WITHOUT_BALCONY);
		Room camera204 = new Room(204, t3, Room.Floor.SECOND, Room.Balcony.WITHOUT_BALCONY);
		Room camera205 = new Room(205, t2, Room.Floor.SECOND, Room.Balcony.WITHOUT_BALCONY);
		Room camera206 = new Room(206, t5, Room.Floor.SECOND, Room.Balcony.WITH_BALCONY);
		Room camera207 = new Room(207, t5, Room.Floor.SECOND, Room.Balcony.WITH_BALCONY);
		Room camera208 = new Room(208, t5, Room.Floor.SECOND, Room.Balcony.WITH_BALCONY);
		Room camera209 = new Room(209, t5, Room.Floor.SECOND, Room.Balcony.WITH_BALCONY);
		Room camera301 = new Room(301, t3, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera302 = new Room(302, t3, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera303 = new Room(303, t4, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera304 = new Room(304, t4, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera305 = new Room(305, t2, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera306 = new Room(306, t2, Room.Floor.THIRD, Room.Balcony.WITHOUT_BALCONY);
		Room camera401 = new Room(401, t2, Room.Floor.FOURTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera402 = new Room(402, t2, Room.Floor.FOURTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera403 = new Room(403, t3, Room.Floor.FOURTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera404 = new Room(404, t2, Room.Floor.FOURTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera405 = new Room(405, t2, Room.Floor.FOURTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera1000 = new Room(1000, t5, Room.Floor.FIFTH, Room.Balcony.WITHOUT_BALCONY);
		Room camera1001 = new Room(10001, t5, Room.Floor.FIFTH, Room.Balcony.WITHOUT_BALCONY);
		roomService.saveRoom(camera101);
		roomService.saveRoom(camera102);
		roomService.saveRoom(camera103);
		roomService.saveRoom(camera104);
		roomService.saveRoom(camera105);
		roomService.saveRoom(camera106);
		roomService.saveRoom(camera107);
		roomService.saveRoom(camera108);
		roomService.saveRoom(camera109);
		roomService.saveRoom(camera110);
		roomService.saveRoom(camera201);
		roomService.saveRoom(camera202);
		roomService.saveRoom(camera203);
		roomService.saveRoom(camera204);
		roomService.saveRoom(camera205);
		roomService.saveRoom(camera206);
		roomService.saveRoom(camera207);
		roomService.saveRoom(camera208);
		roomService.saveRoom(camera209);
		roomService.saveRoom(camera301);
		roomService.saveRoom(camera302);
		roomService.saveRoom(camera303);
		roomService.saveRoom(camera304);
		roomService.saveRoom(camera305);
		roomService.saveRoom(camera306);
		roomService.saveRoom(camera401);
		roomService.saveRoom(camera402);
		roomService.saveRoom(camera403);
		roomService.saveRoom(camera404);
		roomService.saveRoom(camera405);
		roomService.saveRoom(camera1000);
		roomService.saveRoom(camera1001);

		Customer customer = new Customer("Luke", "Skywalker", Customer.Gender.MALE, LocalDate.of(1980, 10, 5), "ITALIA", "ROMA",
				"ROMA", "ITALIA", Customer.Housed.SINGLE_GUEST);
		Document document = new Document(customer, "AK343", Document.DocumentType.CARTA_IDENTITA, Document.Release.QUESTURA, LocalDate.of(2015, 10, 5), LocalDate.of(2025, 10, 5), "ROME", "ROME");

		Customer customer2 = new Customer("Viole", "Green", Customer.Gender.MALE, LocalDate.of(1980, 10, 5), "BRASILE", null,
				null, "BRASILE", Customer.Housed.SINGLE_GUEST);

		customerService.saveCustomer(customer);
		documentService.saveDocument(document);
		customerService.saveCustomer(customer2);

		Product product = new Product("Pepsi", BigDecimal.valueOf(2.50));
		Order order = new Order("AL1234", "ATX", LocalDate.of(2020, 1, 5), LocalDate.of(2022, 1, 20),
				"Pepsi", 200, BigDecimal.valueOf(150));

		productService.saveProduct(product);
		orderService.saveOrder(order);

		Reservation reservation = new Reservation("Skywalker", "email@example.com", "930490", LocalDate.now(), LocalDate.now().plusDays(5),
		0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer), Set.of(camera101, camera102));
		reservationService.saveReservation(reservation);

		Consumption consumption = new Consumption(reservation, product, 2);
		consumptionService.saveConsumption(consumption);

		Reservation reservation2 = new Reservation("Atlas", "email@example.com", "930490", LocalDate.of(2021, 12, 25), LocalDate.of(2022, 1, 5),
				0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer), Set.of(camera201, camera204));
		Reservation reservation3 = new Reservation("Khun", "email@example.com", "930490", LocalDate.of(2022, 1, 18), LocalDate.of(2022, 1, 19),
				0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer), Set.of(camera401));
		Reservation reservation4 = new Reservation("Turtle", "email@example.com", "930490", LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 4),
				0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer), Set.of(camera301, camera302));
		Reservation reservation5 = new Reservation("Green", "email@example.com", "930490", LocalDate.of(2022, 1, 4), LocalDate.of(2022, 1, 7),
				0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer2), Set.of(camera301));
		Reservation reservation6 = new Reservation("Hope", "email@example.com", "930490", LocalDate.of(2022, 1, 28), LocalDate.of(2022, 2, 2),
				0, Reservation.Paid.NOT_PAID, BigDecimal.ZERO,
				Reservation.Board.FULL_BOARD, Reservation.Source.BOOKING, Set.of(customer2), Set.of(camera404));

		reservationService.saveReservation(reservation2);
		reservationService.saveReservation(reservation3);
		reservationService.saveReservation(reservation4);
		reservationService.saveReservation(reservation5);
		reservationService.saveReservation(reservation6);

//		Reservation reservation = reservationService.getReservationById(3L);
//		reservation.getConsumptions().forEach(consumptionService::deleteConsumption);
	}
}