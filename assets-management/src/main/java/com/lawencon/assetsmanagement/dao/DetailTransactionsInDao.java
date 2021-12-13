package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.DetailTransactionsIn;

public interface DetailTransactionsInDao {
	
	List<DetailTransactionsIn> findAll() throws Exception;
	
	DetailTransactionsIn findById(String id) throws Exception;
	
	DetailTransactionsIn saveOrUpdate(DetailTransactionsIn data) throws Exception;
	
	List<DetailTransactionsIn> findByIdHeader(String idHeader) throws Exception;
}
