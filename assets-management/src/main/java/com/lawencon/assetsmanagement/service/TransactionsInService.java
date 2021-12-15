package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;
import com.lawencon.assetsmanagement.model.TransactionsIn;

public interface TransactionsInService {

	List<TransactionsIn> findAll() throws Exception;
	
	TransactionsIn findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqDataHeaderTransactionsInDto data) throws Exception;
	
	
	
}
