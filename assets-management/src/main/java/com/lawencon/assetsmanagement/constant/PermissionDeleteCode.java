package com.lawencon.assetsmanagement.constant;

public enum PermissionDeleteCode {
	DELETE_COMPANY("D-Company"), DELETE_STATUS("D-Status"), DELETE_ROLE("D-Role"), DELETE_LOCATION("D-Location"), DELETE_TYPE("D-Type"), DELETE_CONDITION("D-Condtion"), DELETE_EMPLOYEE("D-Employee"), DELETE_PERMISSION("D-Permission");
	
	private String code;
	
	PermissionDeleteCode(String code) {
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
}
