package com.lawencon.assetsmanagement.dto.permissions;

import java.util.List;

import com.lawencon.assetsmanagement.model.Permissions;

public class FindAllResPemissionsDto {

	private List<Permissions> data;
	
	private String msg;

	public List<Permissions> getData() {
		return data;
	}

	public void setData(List<Permissions> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}