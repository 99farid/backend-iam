package com.lawencon.assetsmanagement.constant;

public enum ResponseMsg {
	SUCCESS_INSERT("Successfull Insert Data"),SUCCESS_UPDATE("Successfull Update Data"), SUCCESS_DELETE("Successfull Delete Data"), FAILED_DELETE("Failed Delete Data");
	
	private String msg;
	
	ResponseMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
