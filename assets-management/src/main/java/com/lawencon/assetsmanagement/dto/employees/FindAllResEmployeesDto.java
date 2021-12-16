package com.lawencon.assetsmanagement.dto.employees;

import java.util.List;

import com.lawencon.assetsmanagement.model.Employees;

public class FindAllResEmployeesDto {

	private List<Employees> data;
	
	private String msg;

	public List<Employees> getData() {
		return data;
	}

	public void setData(List<Employees> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}