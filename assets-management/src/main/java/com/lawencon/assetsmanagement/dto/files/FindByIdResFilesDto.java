package com.lawencon.assetsmanagement.dto.files;

import com.lawencon.assetsmanagement.model.Files;

public class FindByIdResFilesDto {
	private Files data;
	private String msg;
	public Files getData() {
		return data;
	}
	public void setData(Files data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
