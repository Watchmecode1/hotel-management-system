package com.hotel.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Room {
	
	@Id
	private int number;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;
	
	@Column(nullable = false)
	private Floor floor;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Balcony balcony;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private State state = State.AVAILABLE;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Condition condition = Condition.CLEAN;
	
	private String note = "";
	
	@ManyToMany(mappedBy = "rooms")
	private Set<Reservation> reservations;
	
	public enum Floor {
		
		FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5);
		
		private final int floor;
		
		Floor(int floor) {
			this.floor = floor;
		}
		
		public int getFloor() { return floor; }
	}
	
	public enum Balcony { WITHOUT_BALCONY, WITH_BALCONY }
	
	public enum State { AVAILABLE, RESERVED }
	
	public enum Condition { CLEAN, TO_CLEAN }

	public Room(int number, RoomType roomType, Floor floor, Balcony balcony) {
		this.number = number;
		this.roomType = Objects.requireNonNull(roomType);
		this.floor = Objects.requireNonNull(floor);
		this.balcony = Objects.requireNonNull(balcony);
	}
	
	public Room() {}

	public int getNumber() { return number; }

	public void setNumber(int number) {this.number = number;}
	
	public RoomType getRoomType() { return roomType; }

	public void setRoomType(RoomType roomType) { this.roomType = Objects.requireNonNull(roomType); }

	public State getState() { return state; }

	public void getState(State state) { this.state = Objects.requireNonNull(state); }

	public Condition getCondition() { return condition; }

	public void setCondition(Condition condition) { this.condition = Objects.requireNonNull(condition); }

	public Set<Reservation> getReservations() { return reservations; }

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = Objects.requireNonNull(reservations);
	}
	
	public Balcony getBalcony() { return balcony; }

	public void setBalcony(Balcony balcony) { this.balcony = Objects.requireNonNull(balcony); }
	
	public Floor getFloor() { return floor; }

	public void setFloor(Floor floor) { this.floor = Objects.requireNonNull(floor); }
	
	public String getNote() { return note; }
	
	public void setNote(String note) { this.note = note; }

	@Override
	public String toString() {
		return "ROOM " + getNumber() + "(" + getRoomType().getType() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, floor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Room room))
			return false;
		return number == room.number && floor == room.floor;
	}
}