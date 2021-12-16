package com.lawencon.assetsmanagement.dto.transactionsout;

import com.lawencon.assetsmanagement.model.TransactionsOut;

public class FindByIdResTransactionsOutDto {

	private TransactionsOut data;
	
	private String msg;

	public TransactionsOut getData() {
		return data;
	}

	public void setData(TransactionsOut data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}