package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;
import com.lawencon.assetsmanagement.model.TransactionsOut;

public interface TransactionsOutService {

	List<TransactionsOut> findAll() throws Exception;
	
	TransactionsOut findById(String id) throws Exception;
	
	InsertResDto saveOrUpdate(InsertReqDataTransactionsOutDto data) throws Exception;
}
