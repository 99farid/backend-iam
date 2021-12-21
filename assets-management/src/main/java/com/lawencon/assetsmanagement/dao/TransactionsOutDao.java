package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.TransactionsOut;

public interface TransactionsOutDao {

	List<TransactionsOut> findAll() throws Exception;
	
	TransactionsOut findById(String id) throws Exception;
	
	List<TransactionsOut> findAllFilterByIdEmployee() throws Exception;
	
	List<TransactionsOut> findAllFilterByIdLocation() throws Exception;
	
	List<TransactionsOut> findAllFilterByIdGeneralItem() throws Exception;
	
	TransactionsOut saveOrUpdate(TransactionsOut data) throws Exception;
	
	List<TransactionsOut> findAllForPdf() throws Exception;
	
	Integer countData() throws Exception;
	
}
