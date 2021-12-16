package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindAllResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdHeaderResDetailTransactionInDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsin.FindByIdResDetailTransactionInDto;

public interface DetailTransactionsInService {

	FindAllResDetailTransactionInDto findAll() throws Exception;
	
	FindByIdResDetailTransactionInDto findById(String id) throws Exception;
	
	FindByIdHeaderResDetailTransactionInDto findByIdHeader(String idHeader) throws Exception;
}
