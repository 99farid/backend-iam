package com.lawencon.assetsmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.employees.FindAllResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByIdResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByResNipDto;
import com.lawencon.assetsmanagement.model.Employees;

public interface EmployeesService {

	FindAllResEmployeesDto findAll() throws Exception;
	
	FindByIdResEmployeesDto findById(String id) throws Exception;
	
	FindByResNipDto findByNip(String nip) throws Exception;
	
	InsertResDto insert(Employees data) throws Exception;
	
	InsertResDto insertExcel(MultipartFile data) throws Exception;
	
	UpdateResDto update(Employees data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
