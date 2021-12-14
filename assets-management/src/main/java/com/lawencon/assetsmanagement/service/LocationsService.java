package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Locations;

public interface LocationsService {
	
	List<Locations> findAll() throws Exception;
	
	Locations findById(String id) throws Exception;
	
	InsertResDto insert(Locations data) throws Exception;
	
	UpdateResDto update(Locations data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
} 
