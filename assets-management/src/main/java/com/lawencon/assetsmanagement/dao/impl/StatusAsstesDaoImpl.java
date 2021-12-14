package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class StatusAsstesDaoImpl extends BaseDaoImpl<StatusAssets> implements StatusAssetsDao{

	@Override
	public List<StatusAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public StatusAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusAssets saveOrUpdate(StatusAssets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
