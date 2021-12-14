package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.StatusAssets;

public interface StatusAssetsService {
	
	List<StatusAssets> findAll() throws Exception;
	
	StatusAssets findById(String id);
	
	InsertResDto insert(StatusAssets data) throws Exception;
	
	UpdateResDto update(StatusAssets data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
