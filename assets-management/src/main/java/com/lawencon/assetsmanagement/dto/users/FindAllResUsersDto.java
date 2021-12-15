package com.lawencon.assetsmanagement.dto.users;

import java.util.List;

import com.lawencon.assetsmanagement.model.Users;

public class FindAllResUsersDto {

	private List<Users> data;
	
	private String msg;

	public List<Users> getData() {
		return data;
	}

	public void setData(List<Users> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}