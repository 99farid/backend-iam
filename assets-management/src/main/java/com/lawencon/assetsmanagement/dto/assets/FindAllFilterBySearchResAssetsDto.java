package com.lawencon.assetsmanagement.dto.assets;

import java.util.List;

import com.lawencon.assetsmanagement.model.Assets;

public class FindAllFilterBySearchResAssetsDto {
	
	private List<Assets> data;
	private String msg;
	
	public List<Assets> getData() {
		return data;
	}
	public void setData(List<Assets> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
