package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Assets extends BaseEntity {
	
	private static final long serialVersionUID = 5830622787061837014L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@OneToOne
	@JoinColumn(name = "id_item", nullable = false, unique = true)
	private Items item;
	
	@ManyToOne
	@JoinColumn(name = "id_status_asset", nullable = false)
	private StatusAssets statusAsset;
	
	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false)
	private Companies company;
	
	@ManyToOne
	@JoinColumn(name = "id_invoice", nullable = false)
	private Invoices invoice;
	
	@Column(nullable = false)
	private LocalDate expiredDate;
	
	@Column(nullable = false)
	private Files display;

	public Files getDisplay() {
		return display;
	}

	public void setDisplay(Files display) {
		this.display = display;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public StatusAssets getStatusAsset() {
		return statusAsset;
	}

	public void setStatusAsset(StatusAssets statusAsset) {
		this.statusAsset = statusAsset;
	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}

	public Invoices getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoices invoice) {
		this.invoice = invoice;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
