package com.lawencon.assetsmanagement.dto.transactionsin;

import com.lawencon.assetsmanagement.model.TransactionsIn;

public class FindByIdResTransactionsInDto {
	private TransactionsIn data;
	private String msg;
	
	public TransactionsIn getData() {
		return data;
	}
	public void setData(TransactionsIn data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
