package com.lawencon.assetsmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Invoices extends BaseEntity{
	
	private static final long serialVersionUID = -6489013604362874894L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(name = "invoice_date", nullable = false)
	private LocalDate purchaseDate;
	
	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;
	
	@OneToOne
	@JoinColumn(name = "id_invoice_pict")
	private Files invoicePict;
	

	public Files getInvoicePict() {
		return invoicePict;
	}

	public void setInvoicePict(Files invoicePict) {
		this.invoicePict = invoicePict;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
