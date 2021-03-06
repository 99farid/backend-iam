package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllForPdfTrxExpiredDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindAllResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResDetailTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.detailtransactionsout.FindByIdResHeaderDto;

public interface DetailTransactionsOutService {

	FindAllResDetailTransactionsOutDto findAll() throws Exception;
	
	FindByIdResDetailTransactionsOutDto findById(String id) throws Exception;
	
	FindByIdResHeaderDto findByIdHeader(String idHeader) throws Exception;
	
	FindByIdResHeaderDto findByIdHeaderForCheckIn(String idHeader) throws Exception;
	
	void sendReportDueDate() throws Exception;
	
	FindAllForPdfTrxExpiredDto findAllForPdf() throws Exception;

	SendResEmailDto sendFileToEmail() throws Exception;
}
