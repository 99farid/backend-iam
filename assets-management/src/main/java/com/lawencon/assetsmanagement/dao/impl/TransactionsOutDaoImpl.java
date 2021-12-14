package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class TransactionsOutDaoImpl extends BaseDaoImpl<TransactionsOut> implements TransactionsOutDao {

	@Override
	public List<TransactionsOut> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public TransactionsOut findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public TransactionsOut saveOrUpdate(TransactionsOut data) throws Exception {
		return save(data);
	}
	
}
