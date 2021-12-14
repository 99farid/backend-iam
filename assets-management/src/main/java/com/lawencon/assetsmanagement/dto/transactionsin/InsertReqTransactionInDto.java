package com.lawencon.assetsmanagement.dto.transactionsin;

public class InsertReqTransactionInDto {
	
	private InsertReqDataHeaderTransactionInDto data;
	private String msg;
	
	public InsertReqDataHeaderTransactionInDto getData() {
		return data;
	}
	public void setData(InsertReqDataHeaderTransactionInDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
