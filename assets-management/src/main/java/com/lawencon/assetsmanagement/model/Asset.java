package com.lawencon.assetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Asset extends BaseEntity {
	
	private static final long serialVersionUID = 5830622787061837014L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@OneToOne
	@JoinColumn(name = "id_item", nullable = false, unique = true)
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "id_status_asset", nullable = false)
	private StatusAsset statusAsset;
	
	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false)
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "id_invoice", nullable = false)
	private Invoice invoice;
	
	@Column(nullable = false)
	private LocalDate expiredDate;
	
	@Column(nullable = false)
	private File display;

	public File getDisplay() {
		return display;
	}

	public void setDisplay(File display) {
		this.display = display;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public StatusAsset getStatusAsset() {
		return statusAsset;
	}

	public void setStatusAsset(StatusAsset statusAsset) {
		this.statusAsset = statusAsset;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
