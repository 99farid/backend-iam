package com.lawencon.assetsmanagement.constant;

public enum ItemTypesCode {

	GENERAL("GNL"), LICENSE("LCS"), COMPONENT("CPN"), CONSUMABLE("CSM");
	
	private String code;
	
	ItemTypesCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
