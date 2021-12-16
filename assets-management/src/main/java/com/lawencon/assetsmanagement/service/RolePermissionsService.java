package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResRolePermissionsDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindByIdResRolePermissionsDto;

public interface RolePermissionsService {

	FindAllResRolePermissionsDto findAll() throws Exception;
	
	FindByIdResRolePermissionsDto findById(String id) throws Exception;
	
//	UpdateResDto update(RolePermissions data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
