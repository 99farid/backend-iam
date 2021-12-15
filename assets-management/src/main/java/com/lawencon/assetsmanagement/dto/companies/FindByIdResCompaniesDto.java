package com.lawencon.assetsmanagement.dto.companies;

import com.lawencon.assetsmanagement.model.Companies;

public class FindByIdResCompaniesDto {

	private Companies data;
	
	private String msg;

	public Companies getData() {
		return data;
	}

	public void setData(Companies data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}