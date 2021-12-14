package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.ItemTypes;

public interface ItemTypesService {
	
	List<ItemTypes> findAll() throws Exception;
	
	ItemTypes findById(String id) throws Exception;
	
	InsertResDto insert(ItemTypes data) throws Exception;
	
	UpdateResDto update(ItemTypes data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
