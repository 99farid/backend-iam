package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;

public interface DetailTransactionsOutService {

	FindAllResDetailTransactionsOutDto findAll() throws Exception;
	
	FindByIdResDetailTransactionsOutDto findById(String id) throws Exception;
	
	FindByIdResHeaderDto findByIdHeader(String idHeader) throws Exception;
	
	void sendReportDueDate() throws Exception;
}
