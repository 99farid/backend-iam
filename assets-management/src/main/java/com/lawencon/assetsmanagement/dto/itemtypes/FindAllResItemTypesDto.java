package com.lawencon.assetsmanagement.dto.itemtypes;

import java.util.List;

import com.lawencon.assetsmanagement.model.ItemTypes;

public class FindAllResItemTypesDto {
	private List<ItemTypes> data;
	private String msg;
	public List<ItemTypes> getData() {
		return data;
	}
	public void setData(List<ItemTypes> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}	
