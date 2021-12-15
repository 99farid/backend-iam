package com.lawencon.assetsmanagement.dto.conditionassets;

import java.util.List;

import com.lawencon.assetsmanagement.model.ConditionAssets;

public class FindAllResConditionAssetsDto {
	
	private List<ConditionAssets> data;
	private String msg;
	
	public List<ConditionAssets> getData() {
		return data;
	}
	public void setData(List<ConditionAssets> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
