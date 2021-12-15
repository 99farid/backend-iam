package com.lawencon.assetsmanagement.dto.assets;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lawencon.assetsmanagement.dto.files.InsertReqDataFilesDto;

public class InsertReqDataInvoicesDto {
	
	private String id;
	private String code;
	private LocalDate purchaseDate;
	private BigDecimal totalPrice;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
