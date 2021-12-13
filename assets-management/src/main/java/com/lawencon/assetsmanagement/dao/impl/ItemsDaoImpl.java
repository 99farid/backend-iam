package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import com.lawencon.assetsmanagement.dao.ItemsDao;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.base.BaseDaoImpl;

public class ItemsDaoImpl extends BaseDaoImpl<Items> implements ItemsDao{

	@Override
	public List<Items> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Items findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Items saveOrUpdate(Items data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
