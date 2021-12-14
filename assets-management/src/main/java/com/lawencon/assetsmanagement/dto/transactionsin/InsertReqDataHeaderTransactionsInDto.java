package com.lawencon.assetsmanagement.dto.transactionsin;

import java.time.LocalDate;
import java.util.List;

public class InsertReqDataHeaderTransactionsInDto {
	
	private String idTransactionOut;
	private LocalDate checkInDate;
	private List<InsertReqDataDetailTransactionsInDto> detailData;
	
	
	public List<InsertReqDataDetailTransactionsInDto> getDetailData() {
		return detailData;
	}
	public void setDetailData(List<InsertReqDataDetailTransactionsInDto> detailData) {
		this.detailData = detailData;
	}
	public String getIdTransactionOut() {
		return idTransactionOut;
	}
	public void setIdTransactionOut(String idTransactionOut) {
		this.idTransactionOut = idTransactionOut;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	
}
