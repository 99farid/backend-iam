package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Items;

public interface ItemsService {
	
	List<Items> findAll() throws Exception;
	
	Items findById(String id) throws Exception;
	
	InsertResDto insert(Items data) throws Exception;
	
	UpdateResDto update(Items data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
}
