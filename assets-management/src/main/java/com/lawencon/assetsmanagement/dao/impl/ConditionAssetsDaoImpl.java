package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.base.BaseDaoImpl;

public class ConditionAssetsDaoImpl extends BaseDaoImpl<ConditionAssets> implements ConditionAssetsDao{

	@Override
	public List<ConditionAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public ConditionAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ConditionAssets saveOrUpdate(ConditionAssets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	

}
