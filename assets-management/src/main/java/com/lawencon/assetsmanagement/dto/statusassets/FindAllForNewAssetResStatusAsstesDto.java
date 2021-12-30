package com.lawencon.assetsmanagement.dto.statusassets;

import java.util.List;

import com.lawencon.assetsmanagement.model.StatusAssets;

public class FindAllForNewAssetResStatusAsstesDto {
	private List<StatusAssets> data;
	private String msg;
	public List<StatusAssets> getData() {
		return data;
	}
	public void setData(List<StatusAssets> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
