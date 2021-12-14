package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.model.TransactionsIn;
import com.lawencon.base.BaseDaoImpl;

@Repository
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
