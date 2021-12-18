package com.lawencon.assetsmanagement.dto.trackactivity;

import java.util.List;

import com.lawencon.assetsmanagement.model.TrackActivity;

public class FindAllResTrackActivityDto {

	private List<TrackActivity> data;
	
	private String msg;

	public List<TrackActivity> getData() {
		return data;
	}

	public void setData(List<TrackActivity> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}