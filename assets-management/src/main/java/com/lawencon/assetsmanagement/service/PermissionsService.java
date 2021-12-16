package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResFilterByNameDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResPemissionsDto;
import com.lawencon.assetsmanagement.dto.permissions.FindByIdResPermissionsDto;
import com.lawencon.assetsmanagement.model.Permissions;

public interface PermissionsService {

	FindAllResPemissionsDto findAll() throws Exception;
	
	FindByIdResPermissionsDto findById(String id) throws Exception;
	
	FindAllResFilterByNameDto findAllFilterByName(String input) throws Exception;
	
	InsertResDto insert(Permissions data) throws Exception;
	
	UpdateResDto update(Permissions data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
