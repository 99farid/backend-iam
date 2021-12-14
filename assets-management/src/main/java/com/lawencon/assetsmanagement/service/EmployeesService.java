package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Employees;

public interface EmployeesService {

	List<Employees> findAll() throws Exception;
	
	Employees findById(String id) throws Exception;
	
	InsertResDto insert(Employees data) throws Exception;
	
	UpdateResDto update(Employees data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
