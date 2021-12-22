package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllForPdfTrxOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdEmployeeDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdGeneralItemDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdLocationDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindByIdResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;

public interface TransactionsOutService {

	FindAllResTransactionsOutDto findAll() throws Exception;
	
	FindByIdResTransactionsOutDto findById(String id) throws Exception;
	
	FindAllResFilterByIdEmployeeDto findAllFilterByIdEmployee() throws Exception;
	
	FindAllResFilterByIdLocationDto findAllFilterByIdLocation() throws Exception;
	
	FindAllResFilterByIdGeneralItemDto findAllFilterByIdGeneralItem() throws Exception;
	
	InsertResDto insert(InsertReqDataTransactionsOutDto data) throws Exception;
	
	FindAllForPdfTrxOutDto findAllForPdf() throws Exception;
	
	SendResEmailDto sendFileToEmail() throws Exception;
}