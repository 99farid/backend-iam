package com.lawencon.assetsmanagement.dto.detailtransactionsin;

import java.util.List;

import com.lawencon.assetsmanagement.model.DetailTransactionsIn;

public class FindAllResDetailTransactionInDto {
	private List<DetailTransactionsIn> data;
	private String msg;
	public List<DetailTransactionsIn> getData() {
		return data;
	}
	public void setData(List<DetailTransactionsIn> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
