package com.lawencon.assetsmanagement.dto.conditionassets;

import com.lawencon.assetsmanagement.model.ConditionAssets;

public class FindByIdResConditionAssetsDto {
	
	private ConditionAssets data;
	private String msg;
	
	public ConditionAssets getData() {
		return data;
	}
	public void setData(ConditionAssets data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
