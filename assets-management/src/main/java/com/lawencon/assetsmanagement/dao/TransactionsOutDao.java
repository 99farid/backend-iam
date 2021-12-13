package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.TransactionsOut;

public interface TransactionsOutDao {

	List<TransactionsOut> findAll() throws Exception;
	
	TransactionsOut findById(String id) throws Exception;
	
	TransactionsOut saveOrUpdate(TransactionsOut data) throws Exception;
}
