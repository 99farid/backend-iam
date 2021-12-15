package com.lawencon.assetsmanagement.service;

import java.math.BigDecimal;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.items.FindAllResItemsDto;
import com.lawencon.assetsmanagement.dto.items.FindByIdResItemsDto;
import com.lawencon.assetsmanagement.model.Items;

public interface ItemsService {
	
	FindAllResItemsDto findAll() throws Exception;
	
	FindByIdResItemsDto findById(String id) throws Exception;
	
	InsertResDto insert(Items data) throws Exception;
	
	UpdateResDto update(Items data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	BigDecimal getTotalPrice() throws Exception;
}
