package com.lawencon.assetsmanagement.dto.locations;

import com.lawencon.assetsmanagement.model.Locations;

public class FindByIdResLocationsDto {
	private Locations data;
	private String msg;
	public Locations getData() {
		return data;
	}
	public void setData(Locations data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
