package com.lawencon.assetsmanagement.dto.detailtransactionsout;

import com.lawencon.assetsmanagement.model.DetailTransactionsOut;

public class FindByIdResDetailTransactionsOutDto {

	private DetailTransactionsOut data;
	
	private String msg;

	public DetailTransactionsOut getData() {
		return data;
	}

	public void setData(DetailTransactionsOut data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}