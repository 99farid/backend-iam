package com.lawencon.assetsmanagement.dto.roles;

import java.util.List;

import com.lawencon.assetsmanagement.model.Roles;

public class FindAllResRolesDto {

	private List<Roles> data;
	
	private String msg;

	public List<Roles> getData() {
		return data;
	}

	public void setData(List<Roles> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
