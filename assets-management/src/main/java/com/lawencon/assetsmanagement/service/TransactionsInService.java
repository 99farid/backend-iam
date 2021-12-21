package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllForPdfTrxInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindAllResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.FindByIdResTransactionsInDto;
import com.lawencon.assetsmanagement.dto.transactionsin.InsertReqDataHeaderTransactionsInDto;

public interface TransactionsInService {

	FindAllResTransactionsInDto findAll() throws Exception;
	
	FindByIdResTransactionsInDto findById(String id) throws Exception;
	
	InsertResDto insert(InsertReqDataHeaderTransactionsInDto data) throws Exception;
	
	FindAllForPdfTrxInDto findAllForPdf() throws Exception;
}
