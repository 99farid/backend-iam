package com.lawencon.assetsmanagement.dto.transactionsout;

public class InsertReqTransactionsOutDto {

	private InsertReqDataTransactionsOutDto data;
	
	private String msg;

	public InsertReqDataTransactionsOutDto getData() {
		return data;
	}

	public void setData(InsertReqDataTransactionsOutDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
