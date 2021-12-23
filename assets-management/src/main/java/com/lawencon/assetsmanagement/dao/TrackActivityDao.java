package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.TrackActivity;

public interface TrackActivityDao {

	List<TrackActivity> findAll() throws Exception;
	
	TrackActivity findById(String id) throws Exception;
	
	TrackActivity saveOrUpdate(TrackActivity data) throws Exception;
	
	Integer countData() throws Exception;
}