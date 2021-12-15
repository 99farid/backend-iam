package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.RolePermissions;

public interface RolePermissionsService {

	List<RolePermissions> findAll() throws Exception;
	
	RolePermissions findById(String id) throws Exception;
	
//	UpdateResDto update(RolePermissions data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
