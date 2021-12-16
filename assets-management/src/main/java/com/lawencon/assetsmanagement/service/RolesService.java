package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.FindAllResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.FindByIdResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqDataRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;

public interface RolesService {

	FindAllResRolesDto findAll() throws Exception;
	
	FindByIdResRolesDto findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqDataRolesDto data) throws Exception;
	
	UpdateResDto update(UpdateReqRolesDto data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}