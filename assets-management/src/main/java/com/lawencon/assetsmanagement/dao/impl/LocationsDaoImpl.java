package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.base.BaseDaoImpl;

public class LocationsDaoImpl extends BaseDaoImpl<Locations> implements LocationsDao{

	@Override
	public List<Locations> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Locations saveOrUpdate(Locations data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	
	
}
