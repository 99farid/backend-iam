package com.lawencon.assetsmanagement.dto.companies;

import java.util.List;

import com.lawencon.assetsmanagement.model.Companies;

public class FindAllResCompaniesDto {

	private List<Companies> data;
	
	private String msg;

	public List<Companies> getData() {
		return data;
	}

	public void setData(List<Companies> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}