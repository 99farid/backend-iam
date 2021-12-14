package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;
import com.lawencon.assetsmanagement.model.Roles;

public interface RolesService {

	List<Roles> findAll() throws Exception;
	
	Roles findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqRolesDto data) throws Exception;
	
	UpdateResDto update(UpdateReqRolesDto data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}