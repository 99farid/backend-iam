package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResFilterByRoleDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResRolePermissionsDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindByIdResRolePermissionsDto;
import com.lawencon.assetsmanagement.model.RolePermissions;

public interface RolePermissionsService {

	FindAllResRolePermissionsDto findAll() throws Exception;
	
	FindByIdResRolePermissionsDto findById(String id) throws Exception;
	
//	UpdateResDto update(RolePermissions data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	FindAllResFilterByRoleDto findAllFilterByRole(String idRole) throws Exception;
}
