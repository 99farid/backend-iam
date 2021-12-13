package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class EmployeesDaoImpl extends BaseDaoImpl<Employees> implements EmployeesDao {

	@Override
	public List<Employees> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Employees findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Employees saveOrUpdate(Employees data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}