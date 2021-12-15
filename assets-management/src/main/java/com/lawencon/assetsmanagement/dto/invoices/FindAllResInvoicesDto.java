package com.lawencon.assetsmanagement.dto.invoices;

import java.util.List;

import com.lawencon.assetsmanagement.model.Invoices;

public class FindAllResInvoicesDto {
	private List<Invoices> data;
	private String msg;
	public List<Invoices> getData() {
		return data;
	}
	public void setData(List<Invoices> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
