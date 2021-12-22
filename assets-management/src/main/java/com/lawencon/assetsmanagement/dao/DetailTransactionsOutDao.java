package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.DetailTransactionsOut;

public interface DetailTransactionsOutDao {

	List<DetailTransactionsOut> findAll() throws Exception;
	
	DetailTransactionsOut findById(String id) throws Exception;
	
	List<DetailTransactionsOut> findByIdHeader(String idHeader) throws Exception;
	
	DetailTransactionsOut saveOrUpdate(DetailTransactionsOut data) throws Exception;
	
	List<DetailTransactionsOut> findAllForPdf() throws Exception;
}
