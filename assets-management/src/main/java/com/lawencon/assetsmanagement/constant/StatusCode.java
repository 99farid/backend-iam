package com.lawencon.assetsmanagement.constant;

public enum StatusCode {
	DEPLOYABLE("SA1"), UNDEPLOYABLE("SA2"), ARCHIVE("SA3"), PENDING("SA4"), ONASSIGN("SA5");
	
	private String code;
	
	StatusCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
