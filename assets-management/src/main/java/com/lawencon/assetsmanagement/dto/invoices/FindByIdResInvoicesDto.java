package com.lawencon.assetsmanagement.dto.invoices;

import com.lawencon.assetsmanagement.model.Invoices;

public class FindByIdResInvoicesDto {
	private Invoices data;
	private String msg;
	public Invoices getData() {
		return data;
	}
	public void setData(Invoices data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
