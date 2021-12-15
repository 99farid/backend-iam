package com.lawencon.assetsmanagement.dto.users;

import com.lawencon.assetsmanagement.model.Users;

public class FindByIdResUsersDto {

	private Users data;
	
	private String msg;

	public Users getData() {
		return data;
	}

	public void setData(Users data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}