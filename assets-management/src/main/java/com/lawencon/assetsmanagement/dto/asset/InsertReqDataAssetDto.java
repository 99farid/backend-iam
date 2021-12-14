package com.lawencon.assetsmanagement.dto.asset;

import java.time.LocalDate;

import com.lawencon.assetsmanagement.dto.files.InsertReqFilesDto;

public class InsertReqDataAssetDto {
	
	private String code;
	private InsertReqDataItemDto item;
	private String idStatusAsset;
	private String idCompany;
	private InsertReqDataInvoiceDto invoice;
	private LocalDate expiredDate;
	private InsertReqFilesDto idDisplay;
	
	
	public InsertReqFilesDto getIdDisplay() {
		return idDisplay;
	}
	public void setIdDisplay(InsertReqFilesDto idDisplay) {
		this.idDisplay = idDisplay;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public InsertReqDataItemDto getItem() {
		return item;
	}
	public void setItem(InsertReqDataItemDto item) {
		this.item = item;
	}
	public String getIdStatusAsset() {
		return idStatusAsset;
	}
	public void setIdStatusAsset(String idStatusAsset) {
		this.idStatusAsset = idStatusAsset;
	}
	public String getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}
	public InsertReqDataInvoiceDto getInvoice() {
		return invoice;
	}
	public void setInvoice(InsertReqDataInvoiceDto invoice) {
		this.invoice = invoice;
	}
	public LocalDate getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
