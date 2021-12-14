package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Permissions;

public interface PermissionsService {

	List<Permissions> findAll() throws Exception;
	
	Permissions findById(String id) throws Exception;
	
	InsertResDto insert(Permissions data) throws Exception;
	
	UpdateResDto update(Permissions data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
