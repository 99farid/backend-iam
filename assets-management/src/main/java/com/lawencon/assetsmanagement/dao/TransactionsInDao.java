package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.TransactionsIn;

public interface TransactionsInDao {
	
	List<TransactionsIn> findAll() throws Exception;
	
	TransactionsIn findById(String id) throws Exception;
	
	TransactionsIn saveOrUpdate(TransactionsIn data) throws Exception;
	
	Integer countData() throws Exception;
	
	List<TransactionsIn> findAllForPdf() throws Exception;
	
}
