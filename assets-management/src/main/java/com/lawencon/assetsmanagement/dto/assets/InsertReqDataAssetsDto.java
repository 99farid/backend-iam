package com.lawencon.assetsmanagement.dto.assets;

import java.time.LocalDate;

import com.lawencon.assetsmanagement.dto.files.InsertReqDataFilesDto;

public class InsertReqDataAssetsDto {
	
	private String code;
	private InsertReqDataItemsDto item;
	private String idStatusAsset;
	private String idCompany;
	private InsertReqDataInvoicesDto invoice;
	private LocalDate expiredDate;
	private InsertReqDataFilesDto display;
	
	public InsertReqDataFilesDto getDisplay() {
		return display;
	}
	public void setDisplay(InsertReqDataFilesDto display) {
		this.display = display;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public InsertReqDataItemsDto getItem() {
		return item;
	}
	public void setItem(InsertReqDataItemsDto item) {
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
	public InsertReqDataInvoicesDto getInvoice() {
		return invoice;
	}
	public void setInvoice(InsertReqDataInvoicesDto invoice) {
		this.invoice = invoice;
	}
	public LocalDate getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
