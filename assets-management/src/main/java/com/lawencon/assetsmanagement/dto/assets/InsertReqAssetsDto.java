package com.lawencon.assetsmanagement.dto.assets;

public class InsertReqAssetsDto {
	
	private InsertReqDataAssetsDto data;
	private String msg;
	
	public InsertReqDataAssetsDto getData() {
		return data;
	}
	public void setData(InsertReqDataAssetsDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
