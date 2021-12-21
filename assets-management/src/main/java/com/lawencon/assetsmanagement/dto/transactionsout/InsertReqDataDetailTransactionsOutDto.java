package com.lawencon.assetsmanagement.dto.transactionsout;

public class InsertReqDataDetailTransactionsOutDto {
	
	private String idAsset;
	
	private String dueDate;
	
	private Boolean isActive;

	public String getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(String idAsset) {
		this.idAsset = idAsset;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}