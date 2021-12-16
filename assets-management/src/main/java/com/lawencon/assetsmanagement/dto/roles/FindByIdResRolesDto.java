package com.lawencon.assetsmanagement.dto.roles;

import com.lawencon.assetsmanagement.model.Roles;

public class FindByIdResRolesDto {

	private Roles data;
	
	private String msg;

	public Roles getData() {
		return data;
	}

	public void setData(Roles data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}