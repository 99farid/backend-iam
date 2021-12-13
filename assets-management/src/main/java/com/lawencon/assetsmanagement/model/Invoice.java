package com.lawencon.assetsmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Invoice extends BaseEntity{
	
	private static final long serialVersionUID = -6489013604362874894L;

	@Column(length = 12, unique = true, nullable = false)
	private String code;
	
	@Column(name = "purchase_date", nullable = false)
	private LocalDate purchaseDate;
	
	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private File invoicePict;
	

	public File getInvoicePict() {
		return invoicePict;
	}

	public void setInvoicePict(File invoicePict) {
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
