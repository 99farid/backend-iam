package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.model.DetailTransactionsIn;

public interface DetailTransactionsInService {

	List<DetailTransactionsIn> findAll() throws Exception;
	
	DetailTransactionsIn findById(String id) throws Exception;
	
	List<DetailTransactionsIn> findByIdHeader(String idHeader) throws Exception;
}
