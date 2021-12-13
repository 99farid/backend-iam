package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.model.TransactionsIn;
import com.lawencon.base.BaseDaoImpl;

public class TransactionsInDaoImpl extends BaseDaoImpl<TransactionsIn> implements TransactionsInDao {

	@Override
	public List<TransactionsIn> findAll() throws Exception{
		return getAll();
	}

	@Override
	public TransactionsIn findById(String id) throws Exception{
		return getById(id);
	}

	@Override
	public TransactionsIn saveOrUpdate(TransactionsIn data) throws Exception {
		return save(data);
	}
	
}
