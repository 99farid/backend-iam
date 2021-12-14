package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class ItemTypesDaoImpl extends BaseDaoImpl<ItemTypes> implements ItemTypesDao {

	@Override
	public List<ItemTypes> findAll() throws Exception {
		return getAll();
	}

	@Override
	public ItemTypes findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ItemTypes saveOrUpdate(ItemTypes data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
