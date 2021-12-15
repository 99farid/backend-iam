package com.lawencon.assetsmanagement.dto.items;

import java.util.List;

import com.lawencon.assetsmanagement.model.Items;

public class FindAllResItemsDto {
	private List<Items> data;
	private String msg;
	public List<Items> getData() {
		return data;
	}
	public void setData(List<Items> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
