package com.hotel.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Customer {
	
	public static int AGE_CHILD = 3;
	public static int AGE_MINOR = 14;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false)
	private String stateOfBirth;
	
	private String birthplace;
	
	private String countyOfBirth;
	
	@Column(nullable = false)
	private String citizenship;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Age age;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Housed housed;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Document document;
	
	@ManyToMany(mappedBy = "customers")
	private Set<Reservation> reservations;
	
	public enum Gender {
		MALE(1), FEMALE(2);
		
		public final int number;
		
		Gender(int number) {
			this.number = number;
		}
		
		public int getNumber() { return number; }
	}
	
	public enum Age {CHILD, MINOR, ADULT}
	
	public enum Housed {
		
		SINGLE_GUEST(16), HOUSEHOLDER(17), GROUP_LEADER(18), RELATIVE(19), GROUP_MEMBER(20);
		
		private final int number;
		
		Housed(int number) {
			this.number = number;
		}
		
		public int getNumber() { return number; }
	}
	
	public Customer(String name, String surname, Gender gender, LocalDate dateOfBirth, String stateOfBirth, String birthplace,
					String countyOfBirth, String citizenship, Housed housed) {
		this.name = Objects.requireNonNull(name);
		this.surname = Objects.requireNonNull(surname);
		this.gender = gender;
		this.dateOfBirth = Objects.requireNonNull(dateOfBirth);
		this.stateOfBirth = Objects.requireNonNull(stateOfBirth);
		this.birthplace = birthplace;
		this.countyOfBirth = countyOfBirth;
		this.citizenship = Objects.requireNonNull(citizenship);
		this.housed = Objects.requireNonNull(housed);
		setAge();
	}
	
	public Customer() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = Objects.requireNonNull(id); }

	public Set<Reservation> getReservations() { return reservations; }

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = Objects.requireNonNull(reservations);
	}

	public Document getDocument() { return document; }

	public void setDocument(Document document) { this.document = Objects.requireNonNull(document); }

	public String getName() { return name; }

	public void setName(String name) { this.name = Objects.requireNonNull(name); }

	public String getSurname() { return surname; }

	public void setSurname(String surname) { this.surname = Objects.requireNonNull(surname); }
	
	public Gender getGender() { return gender; }

	public void setGender(Gender gender) { this.gender = Objects.requireNonNull(gender); }

	public LocalDate getDateOfBirth() { return dateOfBirth; }

	public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = Objects.requireNonNull(dateOfBirth); }
	
	public String getBirthplace() { return birthplace; }

	public void setBirthplace(String birthplace) { this.birthplace = birthplace; }

	public String getCountyOfBirth() { return countyOfBirth; }

	public void setCountyOfBirth(String countyOfBirth) { this.countyOfBirth = countyOfBirth; }
	
	public Age getAge() { return age; }
	
	private void setAge() {
		age = dateOfBirth.plusYears(AGE_MINOR).isBefore(LocalDate.now()) ? Age.ADULT : dateOfBirth.plusYears(AGE_CHILD).isBefore(LocalDate.now()) ? Age.MINOR : Age.CHILD;
	}
	
	public String getStateOfBirth() { return stateOfBirth; }
	
	public void setStateOfBirth(String stateOfBirth) { this.stateOfBirth = stateOfBirth; }
	
	public String getCitizenship() { return citizenship; }
	
	public void setCitizenship(String citizenship) { this.citizenship = citizenship; }
	
	public Housed getHoused() { return housed; }
	
	public void setHoused(Housed housed) { this.housed = housed; }

	@Override
	public String toString() {
		return getSurname() + " " + getName() + " " + getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citizenship, surname, birthplace, dateOfBirth, age, id, name, countyOfBirth,
				gender, stateOfBirth);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer customer))
			return false;
		return Objects.equals(citizenship, customer.citizenship) && Objects.equals(surname, customer.surname)
				&& Objects.equals(birthplace, customer.birthplace)
				&& Objects.equals(dateOfBirth, customer.dateOfBirth) && age == customer.age
				&& Objects.equals(id, customer.id) && Objects.equals(name, customer.name)
				&& Objects.equals(countyOfBirth, customer.countyOfBirth) && gender == customer.gender
				&& Objects.equals(stateOfBirth, customer.stateOfBirth);
	}
}