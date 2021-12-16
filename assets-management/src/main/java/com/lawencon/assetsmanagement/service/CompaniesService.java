package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.companies.FindAllResCompaniesDto;
import com.lawencon.assetsmanagement.dto.companies.FindByIdResCompaniesDto;
import com.lawencon.assetsmanagement.model.Companies;

public interface CompaniesService {

	FindAllResCompaniesDto findAll() throws Exception;
	
	FindByIdResCompaniesDto findById(String id) throws Exception;
	
	InsertResDto insert(Companies data) throws Exception;
	
	UpdateResDto update(Companies data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}