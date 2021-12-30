package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllFilterBySearchResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllForNewAssetResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindAllResStatusAsstesDto;
import com.lawencon.assetsmanagement.dto.statusassets.FindByIdResStatusAsstesDto;
import com.lawencon.assetsmanagement.model.StatusAssets;

public interface StatusAssetsService {
	
	FindAllResStatusAsstesDto findAll() throws Exception;
	
	FindByIdResStatusAsstesDto findById(String id) throws Exception;
	
	InsertResDto insert(StatusAssets data) throws Exception;
	
	UpdateResDto update(StatusAssets data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	FindAllFilterBySearchResStatusAsstesDto findAllFilterBySearch(String input) throws Exception;
	
	FindAllForNewAssetResStatusAsstesDto findAllForNewAsset() throws Exception;
}
