package com.lawencon.assetsmanagement.dto.statusassets;

import com.lawencon.assetsmanagement.model.StatusAssets;

public class FindByIdResStatusAsstesDto {
	private StatusAssets data;
	private String msg;
	public StatusAssets getData() {
		return data;
	}
	public void setData(StatusAssets data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
