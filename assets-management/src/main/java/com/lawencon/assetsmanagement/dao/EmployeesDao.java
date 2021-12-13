package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Employees;

public interface EmployeesDao {

	List<Employees> findAll() throws Exception;
	
	Employees findById(String id) throws Exception;
	
	Employees saveOrUpdate(Employees data) throws Exception;
	
	boolean removeById(String id) throws Exception;
}
