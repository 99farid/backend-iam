package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;

public interface DetailTransactionsOutService {

	List<DetailTransactionsOut> findAll() throws Exception;
	
	DetailTransactionsOut findById(String id) throws Exception;
	
	List<DetailTransactionsOut> findByIdHeader(String idHeader) throws Exception;
	
	InsertResDto saveOrUpdate(InsertReqDataDetailTransactionsOutDto data) throws Exception;
}
