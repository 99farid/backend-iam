package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.itemtypes.FindAllResItemTypesDto;
import com.lawencon.assetsmanagement.dto.itemtypes.FindByIdResItemTypesDto;
import com.lawencon.assetsmanagement.model.ItemTypes;

public interface ItemTypesService {
	
	FindAllResItemTypesDto findAll() throws Exception;
	
	FindByIdResItemTypesDto findById(String id) throws Exception;
	
	InsertResDto insert(ItemTypes data) throws Exception;
	
	UpdateResDto update(ItemTypes data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
