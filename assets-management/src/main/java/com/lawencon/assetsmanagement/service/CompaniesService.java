package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Companies;

public interface CompaniesService {

	List<Companies> findAll() throws Exception;
	
	Companies findById(String id) throws Exception;
	
	InsertResDto insert(Companies data) throws Exception;
	
	UpdateResDto update(Companies data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}