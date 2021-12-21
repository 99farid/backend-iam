package com.lawencon.assetsmanagement.constant;

public enum StatusCode {
	DEPLOYABLE("SA1"), UNDEPLOYABLE("SA2"), ARCHIVE("SA4"), PENDING("SA5"), ONASSIGN("SA3");
	
	private String code;
	
	StatusCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
