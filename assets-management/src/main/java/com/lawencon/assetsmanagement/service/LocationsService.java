package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.locations.FindAllFilterBySearchResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindAllResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindByIdResLocationsDto;
import com.lawencon.assetsmanagement.model.Locations;

public interface LocationsService {
	
	FindAllResLocationsDto findAll() throws Exception;
	
	FindByIdResLocationsDto findById(String id) throws Exception;
	
	FindAllFilterBySearchResLocationsDto findAllFilterBySearch(String input) throws Exception;

	InsertResDto insert(Locations data) throws Exception;
	
	UpdateResDto update(Locations data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;	
} 
