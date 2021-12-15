package com.lawencon.assetsmanagement.dto.detailtransactionsout;

import java.util.List;

import com.lawencon.assetsmanagement.model.DetailTransactionsOut;

public class FindByIdResHeaderDto {

	private List<DetailTransactionsOut> data;
	
	private String msg;

	public List<DetailTransactionsOut> getData() {
		return data;
	}

	public void setData(List<DetailTransactionsOut> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}