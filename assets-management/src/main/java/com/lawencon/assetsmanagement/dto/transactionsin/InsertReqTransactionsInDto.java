package com.lawencon.assetsmanagement.dto.transactionsin;

public class InsertReqTransactionsInDto {
	
	private InsertReqDataHeaderTransactionsInDto data;
	private String msg;
	
	public InsertReqDataHeaderTransactionsInDto getData() {
		return data;
	}
	public void setData(InsertReqDataHeaderTransactionsInDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
