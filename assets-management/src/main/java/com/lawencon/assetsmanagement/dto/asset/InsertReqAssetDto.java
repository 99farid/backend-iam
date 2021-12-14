package com.lawencon.assetsmanagement.dto.asset;

public class InsertReqAssetDto {
	
	private InsertReqDataAssetDto data;
	private String msg;
	
	public InsertReqDataAssetDto getData() {
		return data;
	}
	public void setData(InsertReqDataAssetDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
