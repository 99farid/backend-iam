package com.lawencon.assetsmanagement.constant;

public enum GeneralTemplateCode {
	SEND_PASSWORD("SEND_PASSWORD"), DUE_DATE("DUE_DATE"), SEND_REPORTS("SEND_REPORTS");
	
	private String code;
	GeneralTemplateCode (String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
