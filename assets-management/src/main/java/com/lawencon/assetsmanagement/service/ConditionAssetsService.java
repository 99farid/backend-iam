package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.ConditionAssets;

public interface ConditionAssetsService {
	
	List<ConditionAssets> findAll() throws Exception;
	
	ConditionAssets findById(String id) throws Exception;
	
	InsertResDto insert(ConditionAssets data) throws Exception;
	
	UpdateResDto update(ConditionAssets data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
