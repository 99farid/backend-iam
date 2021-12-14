package com.lawencon.assetsmanagement.dto.transactionsout;

public class InsertReqDataDetailTransactionsOutDto {
	
	private String idAsset;
	
	private String checkOutDate;
	
	private Boolean isActive;

	public String getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(String idAsset) {
		this.idAsset = idAsset;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}