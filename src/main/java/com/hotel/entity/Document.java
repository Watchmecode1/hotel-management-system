package com.hotel.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Document {
	
	@Id
	@Column(name = "customer_id")
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Release releasedBy;
	
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(nullable = false)
	private LocalDate expirationDate;
	
	@Column(nullable = false)
	private String placeOfIssue;
	
	private String provinceOfIssue;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public enum DocumentType {
		CARTA_IDENTITA_ELETTRONICA("IDELE"), CARTA_IDENTITA("IDENT"), PASSAPORTO("PASDI"), PATENTE_DI_GUIDA("PATEN");
		
		public String code;
		
		DocumentType(String code) {
			this.code = code;
		}
		
		public String getCode() { return code; }
	}
	
	public enum Release { QUESTURA, PREFETTURA, MIT_UCO }

	public Document(Customer customer, String number, DocumentType documentType, Release releasedBy, LocalDate releaseDate, LocalDate expirationDate, String placeOfIssue, String provinceOfIssue) {
		this.customer = Objects.requireNonNull(customer);
		this.number = Objects.requireNonNull(number);
		this.documentType = Objects.requireNonNull(documentType);
		this.releasedBy = Objects.requireNonNull(releasedBy);
		this.releaseDate = Objects.requireNonNull(releaseDate);
		this.expirationDate = Objects.requireNonNull(expirationDate);
		this.placeOfIssue = Objects.requireNonNull(placeOfIssue);
		this.provinceOfIssue = provinceOfIssue;
	}
	
	public Document() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = Objects.requireNonNull(id); }

	public Customer getCustomer() { return customer; }

	public void setCustomer(Customer customer) { this.customer = customer; }

	public String getNumber() { return number; }

	public void setNumber(String number) { this.number = number; }

	public Release getReleasedBy() { return releasedBy; }

	public void setReleasedBy(Release releasedBy) { this.releasedBy = Objects.requireNonNull(releasedBy); }

	public DocumentType getDocumentType() { return documentType; }

	public void setDocumentType(DocumentType documentType) { this.documentType = Objects.requireNonNull(documentType); }
	
	public LocalDate getReleaseDate() { return releaseDate; }
	
	public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
	
	public LocalDate getExpirationDate() { return expirationDate; }
	
	public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
	
	public String getPlaceOfIssue() { return placeOfIssue; }
	
	public void setPlaceOfIssue(String placeOfIssue) { this.placeOfIssue = placeOfIssue; }
	
	public String getProvinceOfIssue() { return provinceOfIssue; }
	
	public void setProvinceOfIssue(String provinceOfIssue) { this.provinceOfIssue = provinceOfIssue; }
	
	@Override
	public String toString() {
		return documentType + " " + number + " " + releasedBy;
	}
}