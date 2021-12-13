package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.base.BaseDaoImpl;

public class FilesDaoImpl extends BaseDaoImpl<Files> implements FilesDao{

	@Override
	public List<Files> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Files findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Files saveOrUpdate(Files data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	
}
