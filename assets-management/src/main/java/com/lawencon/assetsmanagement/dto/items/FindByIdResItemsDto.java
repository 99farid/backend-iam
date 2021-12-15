package com.lawencon.assetsmanagement.dto.items;

import com.lawencon.assetsmanagement.model.Items;

public class FindByIdResItemsDto {
	private Items data;
	private String msg;
	public Items getData() {
		return data;
	}
	public void setData(Items data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
