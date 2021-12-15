package com.lawencon.assetsmanagement.dto.itemtypes;

import com.lawencon.assetsmanagement.model.ItemTypes;

public class FindByIdResItemTypesDto {
	private ItemTypes data;
	private String msg;
	public ItemTypes getData() {
		return data;
	}
	public void setData(ItemTypes data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
