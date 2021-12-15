package com.lawencon.assetsmanagement.dto.locations;

import java.util.List;

import com.lawencon.assetsmanagement.model.Locations;

public class FindAllResLocationsDto {
	private List<Locations> data;
	private String msg;
	public List<Locations> getData() {
		return data;
	}
	public void setData(List<Locations> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
