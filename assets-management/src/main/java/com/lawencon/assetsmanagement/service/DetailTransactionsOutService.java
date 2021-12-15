package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;

public interface DetailTransactionsOutService {

	FindAllDetailTransactionsOutDto findAll() throws Exception;
	
	FindByIdDetailTransactionsOutDto findById(String id) throws Exception;
	
	FindByIdResHeaderDto findByIdHeader(String idHeader) throws Exception;
}
