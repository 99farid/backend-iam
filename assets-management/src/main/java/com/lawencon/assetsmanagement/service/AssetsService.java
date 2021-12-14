package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.model.Assets;

public interface AssetsService {
	
	List<Assets> findAll() throws Exception;
	
	Assets findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqDataAssetsDto data) throws Exception;
	
	UpdateResDto update(Assets data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	
}
