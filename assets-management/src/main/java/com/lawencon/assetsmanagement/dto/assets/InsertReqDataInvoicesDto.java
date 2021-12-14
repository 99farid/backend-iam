package com.lawencon.assetsmanagement.dto.assets;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lawencon.assetsmanagement.dto.files.InsertReqFilesDto;

public class InsertReqDataInvoicesDto {
	
	private String code;
	private LocalDate purchaseDate;
	private BigDecimal totalPrice;
	private InsertReqFilesDto invoicePict;
	
	public InsertReqFilesDto getInvoicePict() {
		return invoicePict;
	}
	public void setInvoicePict(InsertReqFilesDto invoicePict) {
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
