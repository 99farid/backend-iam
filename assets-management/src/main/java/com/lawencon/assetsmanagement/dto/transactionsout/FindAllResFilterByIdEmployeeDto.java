package com.lawencon.assetsmanagement.dto.transactionsout;

import java.util.List;

import com.lawencon.assetsmanagement.model.TransactionsOut;

public class FindAllResFilterByIdEmployeeDto {

	private List<TransactionsOut> data;
	
	private String msg;

	public List<TransactionsOut> getData() {
		return data;
	}

	public void setData(List<TransactionsOut> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}