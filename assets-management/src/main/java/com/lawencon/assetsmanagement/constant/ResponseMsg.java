package com.lawencon.assetsmanagement.constant;

public enum ResponseMsg {
	SUCCESSINSERT("Successfull Insert Data"),SUCCESSUPDATE("Successfull Insert Data");
	
	private String msg;
	
	ResponseMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
