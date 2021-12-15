package com.lawencon.assetsmanagement.dto.profileusers;

import com.lawencon.assetsmanagement.model.ProfileUsers;

public class FindByIdResProfileUsersDto {

	private ProfileUsers data;

	private String msg;

	public ProfileUsers getData() {
		return data;
	}

	public void setData(ProfileUsers data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}