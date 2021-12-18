package com.lawencon.assetsmanagement.dto.trackactivity;

import com.lawencon.assetsmanagement.model.TrackActivity;

public class FindByIdResTrackActivityDto {

	private TrackActivity data;
	
	private String msg;

	public TrackActivity getData() {
		return data;
	}

	public void setData(TrackActivity data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}