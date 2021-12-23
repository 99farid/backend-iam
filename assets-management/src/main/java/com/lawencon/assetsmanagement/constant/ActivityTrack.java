package com.lawencon.assetsmanagement.constant;

public enum ActivityTrack {

	INSERT_ASSET("Insert Asset"), UPDATE_ASSET("Update Asset"), 
	INSERT_TRANSACTIONIN("Insert Transaction In"), INSERT_TRANSACTIONOUT("Insert Transaction Out"), CODE("TA");
	
	private String name;
	
	ActivityTrack(String name) {
		this.name = name;
	} 
	
	public String getName() {
		return this.name;
	}
}
