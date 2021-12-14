package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.PermissionsDetail;

public interface PermissionsDetailService {

	List<PermissionsDetail> findAll() throws Exception;
	
	PermissionsDetail findById(String id) throws Exception;
	
	InsertResDto insert(PermissionsDetail data) throws Exception;
	
	UpdateResDto update(PermissionsDetail data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
