package com.lawencon.assetsmanagement.dto.employees;

import com.lawencon.assetsmanagement.model.Employees;

public class FindByResNipDto {

	private Employees data;
	
	private String msg;

	public Employees getData() {
		return data;
	}

	public void setData(Employees data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}