package com.hotel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.hotel.entity.Customer.Age;
import com.hotel.exception.GuiInputFieldValueException;
import com.hotel.service.RoomService;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;
	@Column(nullable = false)
	private int numberOfPets;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Paid paid;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Board board;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Source source;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String email;
	
	private BigDecimal deposit;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Customer> customers;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Room> rooms;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "reservation")
	private Set<Consumption> consumptions;
	
	public enum Paid { NOT_PAID, DEPOSIT, PAID }
	
	public enum Board { HALF_BOARD, FULL_BOARD, BNB }
	
	public enum Source { BOOKING, HOTEL }
	
	public Reservation(String surname, String email, String phoneNumber, LocalDate startDate, LocalDate endDate,
					   int numberOfPets, Paid paid, BigDecimal deposit,
					   Board board, Source source, Set<Customer> customers, Set<Room> rooms) {
		this.surname = Objects.requireNonNull(surname);
		this.email = Objects.requireNonNull(email);
		this.phoneNumber = Objects.requireNonNull(phoneNumber);
		this.startDate = Objects.requireNonNull(startDate);
		this.endDate = Objects.requireNonNull(endDate);
		this.numberOfPets = numberOfPets;
		this.paid = Objects.requireNonNull(paid);
		this.deposit = Objects.requireNonNull(deposit);
		this.board = Objects.requireNonNull(board);
		this.source = Objects.requireNonNull(source);
		this.customers = Objects.requireNonNull(customers);
		setRooms(rooms);
	}

	public Reservation() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public LocalDate getStartDate() { return startDate; }

	public void setStartDate(LocalDate startDate) { this.startDate = Objects.requireNonNull(startDate); }

	public LocalDate getEndDate() { return endDate; }

	public void setEndDate(LocalDate endDate) { this.endDate = Objects.requireNonNull(endDate); }

	public int getNumberOfPets() { return numberOfPets; }

	public void setNumberOfPets(int numberOfPets) throws IllegalArgumentException {
		if(numberOfPets < 0) throw new IllegalArgumentException("Il numero di animali deve essere 0 o pi�");
		this.numberOfPets = numberOfPets;
	}

	public Paid getPaid() { return paid; }

	public void setPaid(Paid paid) { this.paid = Objects.requireNonNull(paid); }

	public Board getBoard() { return board; }

	public void setBoard(Board board) { this.board = Objects.requireNonNull(board); }

	public Set<Customer> getCustomers() { return customers; }

	public void setCustomers(Set<Customer> customers) { this.customers = Objects.requireNonNull(customers); }

	public Set<Room> getRooms() { return rooms; }

	public void setRooms(Set<Room> rooms) {
		Set<Room> availableRooms = new RoomService().findAvailableRooms(startDate, endDate);
		//TODO gestire in qualche modo la modifica di una prenotazione sulle stesse camere e altri giorni
		if(this.rooms != null)
			availableRooms.addAll(this.rooms);
		if(rooms == null || !availableRooms.containsAll(rooms))
			throw new GuiInputFieldValueException("SELEZIONA DELLE CAMERE PRENOTABILI PER LE DATE SELEZIONATE");
		this.rooms = Objects.requireNonNull(rooms);
	}

	public Set<Consumption> getConsumptions() { return consumptions; }

	public void setConsumptions(Set<Consumption> consumptions) {
		this.consumptions = Objects.requireNonNull(consumptions);
	}
	
	public Source getSource() { return source; }
	
	public void setSource(Source source) { this.source = source; }
	
	public void addConsumption(Consumption consumption) { consumptions.add(consumption); }
	
	public void addCustomer(Customer customer) { customers.add(customer); }
	
	public String getSurname() { return surname; }
	
	public void setSurname(String surname) { this.surname = surname; }
	
	@Override
	public String toString() {
		return surname + " - From: " + startDate.toString() + " to: " + endDate.toString();
	}
	
	public int getNumberOfAdults() {
		return (int) customers.stream().filter(c -> c.getAge() == Age.ADULT).count();
	}
	
	public int getNumberOfMinors() {
		return (int) customers.stream().filter(c -> c.getAge() == Age.MINOR).count();
	}
	
	public int getNumberOfChilds() {
		return (int) customers.stream().filter(c -> c.getAge() == Age.CHILD).count();
	}
	
	public String getPhoneNumber() { return phoneNumber; }
	
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public BigDecimal getTotalCost() {
		int totalDays = (int) ChronoUnit.DAYS.between(getStartDate(), getEndDate());
		BigDecimal roomPrices = BigDecimal.valueOf(rooms.stream().mapToInt(room -> room.getRoomType().getPrice().intValue() * totalDays).sum());
		BigDecimal consumptionPrices = BigDecimal.ZERO;
		if(consumptions != null)
			consumptionPrices = BigDecimal.valueOf(consumptions.stream().mapToDouble(consumption -> consumption.getQuantity() * consumption.getProduct().getPrice().doubleValue()).sum());
		return roomPrices.add(consumptionPrices);
	}
	
	public BigDecimal getDeposit() { return deposit; }
	
	public void setDeposit(BigDecimal deposit) { this.deposit = deposit; }
	
	public String getPdf() {
		StringBuilder sb = new StringBuilder();
		sb.append("PRENOTAZIONE ID: ").append(id).append("\n")
		.append("FONTE: ").append(source).append("\n")
		.append("TIPO PENSIONE: ").append(board).append("\n")
		.append("NOMINATIVO: ").append(surname.toUpperCase()).append("\n")
		.append("EMAIL: ").append(email).append("\n")
		.append("TELEFONO: ").append(phoneNumber).append("\n")
		.append("DATA ARRIVO: ").append(startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
		.append("DATA PARTENZA: ").append(endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
		.append("NUMERO PERSONE: ").append(customers.size()).append("\n")
		.append("ADULTI: ").append(getNumberOfAdults()).append("\n")
		.append("MINORI: ").append(getNumberOfMinors()).append("\n")
		.append("BAMBINI: ").append(getNumberOfChilds()).append("\n")
		.append("NUMERO ANIMALI: ").append(getNumberOfPets()).append("\n")
		.append("CAMERE OCCUPATE: ");
		
		for (Room room : getRooms())
			sb.append(room.getNumber()).append(" ");
		
		sb.append("\n")
		.append("TOTALE SOGGIORNO: �. ").append(getTotalCost()).append("\n");
		
		switch(getPaid()) {
			case PAID -> sb.append("PAGATO INTERAMENTE");
			case NOT_PAID -> sb.append("PAGAMENTO DA EFFETTUARE IN STRUTTURA");
			case DEPOSIT -> sb.append("DEPOSITO VERSATO: ").append(deposit.doubleValue());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(surname, endDate, startDate, email, id, phoneNumber, board);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Reservation reservation))
			return false;
		return Objects.equals(surname, reservation.surname) && Objects.equals(endDate, reservation.endDate)
				&& Objects.equals(startDate, reservation.startDate) && Objects.equals(email, reservation.email)
				&& Objects.equals(id, reservation.id) && Objects.equals(phoneNumber, reservation.phoneNumber)
				&& board == reservation.board;
	}
}