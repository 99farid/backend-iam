package com.lawencon.assetsmanagement.dto.roles;

public class InsertReqRolesDto {

	private InsertReqDataRolesDto data;
	
	private String msg;
	
	public InsertReqDataRolesDto getData() {
		return data;
	}
	public void setData(InsertReqDataRolesDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}