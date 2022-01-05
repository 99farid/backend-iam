package com.lawencon.assetsmanagement.dto.rolepermissions;

import java.util.List;

import com.lawencon.assetsmanagement.model.RolePermissions;

public class FindAllResFilterByRoleCodeDto {

	private List<RolePermissions> data;
	
	private String msg;

	public List<RolePermissions> getData() {
		return data;
	}

	public void setData(List<RolePermissions> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
