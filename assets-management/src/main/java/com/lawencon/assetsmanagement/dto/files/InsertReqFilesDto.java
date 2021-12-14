package com.lawencon.assetsmanagement.dto.files;

public class InsertReqFilesDto {
	private InsertReqDataFilesDto data;
	private String msg;
	
	public InsertReqDataFilesDto getData() {
		return data;
	}
	public void setData(InsertReqDataFilesDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
