package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class AssetsDaoImpl extends BaseDaoImpl<Assets> implements AssetsDao{

	@Override
	public List<Assets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Assets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Assets saveOrUpdate(Assets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	
}
