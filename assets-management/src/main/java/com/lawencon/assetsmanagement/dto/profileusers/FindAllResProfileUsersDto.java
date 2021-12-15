package com.lawencon.assetsmanagement.dto.profileusers;

import java.util.List;

import com.lawencon.assetsmanagement.model.ProfileUsers;

public class FindAllResProfileUsersDto {

	private List<ProfileUsers> data;
	
	private String msg;

	public List<ProfileUsers> getData() {
		return data;
	}

	public void setData(List<ProfileUsers> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
