package com.lawencon.assetsmanagement.dto.permissions;

import com.lawencon.assetsmanagement.model.Permissions;

public class FindByIdResPermissionsDto {

	private Permissions data;
	
	private String msg;

	public Permissions getData() {
		return data;
	}

	public void setData(Permissions data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}