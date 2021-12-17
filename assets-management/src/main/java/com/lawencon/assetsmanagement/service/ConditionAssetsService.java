package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindAllResConditionAssetsDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindByIdResConditionAssetsDto;
import com.lawencon.assetsmanagement.model.ConditionAssets;

public interface ConditionAssetsService {
	
	FindAllResConditionAssetsDto findAll() throws Exception;
	
	FindByIdResConditionAssetsDto findById(String id) throws Exception;
	
	InsertResDto insert(ConditionAssets data) throws Exception;
	
	UpdateResDto update(ConditionAssets data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	
}
