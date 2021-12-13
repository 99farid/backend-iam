package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class CompaniesDaoImpl extends BaseDaoImpl<Companies> implements CompaniesDao {

	@Override
	public List<Companies> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Companies findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Companies saveOrUpdate(Companies data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}