package com.lawencon.assetsmanagement.constant;

public enum HeaderCode {
	TRANSACTIONIN("TRIN"), TRANSACTIONOUT("TROT");
	private String code;
	
	HeaderCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
