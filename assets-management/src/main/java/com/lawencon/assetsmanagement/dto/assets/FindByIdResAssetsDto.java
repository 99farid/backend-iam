package com.lawencon.assetsmanagement.dto.assets;

import com.lawencon.assetsmanagement.model.Assets;

public class FindByIdResAssetsDto {
	private Assets data;
	private String msg;

	
	public Assets getData() {
		return data;
	}
	public void setData(Assets data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
