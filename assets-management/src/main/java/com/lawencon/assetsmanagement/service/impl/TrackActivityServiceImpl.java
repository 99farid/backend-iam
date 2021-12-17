package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindByIdResTrackActivityDto;
import com.lawencon.assetsmanagement.service.TrackActivityService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class TrackActivityServiceImpl extends BaseServiceImpl implements TrackActivityService {

	@Autowired
	private TrackActivityDao trackActivityDao;
	
	@Override
	public FindAllResTrackActivityDto findAll() throws Exception {
		FindAllResTrackActivityDto result = new FindAllResTrackActivityDto();
		result.setData(trackActivityDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResTrackActivityDto findById(String id) throws Exception {
		FindByIdResTrackActivityDto result = new FindByIdResTrackActivityDto();
		result.setData(trackActivityDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	
}
