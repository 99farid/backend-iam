package com.lawencon.assetsmanagement.dto.rolepermissions;

import com.lawencon.assetsmanagement.model.RolePermissions;

public class FindByIdResRolePermissionsDto {

	private RolePermissions data;
	
	private String msg;

	public RolePermissions getData() {
		return data;
	}

	public void setData(RolePermissions data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}