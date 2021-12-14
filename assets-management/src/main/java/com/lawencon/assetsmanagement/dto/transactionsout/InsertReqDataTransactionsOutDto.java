package com.lawencon.assetsmanagement.dto.transactionsout;

import java.util.List;

public class InsertReqDataTransactionsOutDto {
	
	private String idEmployee;
	
	private String idLocation;
	
	private String idGeneralItem;
	
	private List<InsertReqDataDetailTransactionsOutDto> dataDetail;

	private Boolean isActive;

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public String getIdGeneralItem() {
		return idGeneralItem;
	}

	public void setIdGeneralItem(String idGeneralItem) {
		this.idGeneralItem = idGeneralItem;
	}

	public List<InsertReqDataDetailTransactionsOutDto> getDataDetail() {
		return dataDetail;
	}

	public void setDataDetail(List<InsertReqDataDetailTransactionsOutDto> dataDetail) {
		this.dataDetail = dataDetail;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}