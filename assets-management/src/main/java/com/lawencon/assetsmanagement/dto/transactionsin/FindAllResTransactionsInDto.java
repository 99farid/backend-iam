package com.lawencon.assetsmanagement.dto.transactionsin;

import java.util.List;

import com.lawencon.assetsmanagement.model.TransactionsIn;

public class FindAllResTransactionsInDto {
	private List<TransactionsIn> data;
	private String msg;
	
	public List<TransactionsIn> getData() {
		return data;
	}
	public void setData(List<TransactionsIn> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
