package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindByIdResTrackActivityDto;

public interface TrackActivityService {

	FindAllResTrackActivityDto findAll() throws Exception;
	
	FindByIdResTrackActivityDto findById(String id) throws Exception;
}
