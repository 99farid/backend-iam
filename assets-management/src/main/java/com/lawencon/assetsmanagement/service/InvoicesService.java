package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindByIdResInvoicesDto;
import com.lawencon.assetsmanagement.model.Invoices;

public interface InvoicesService {
	FindAllResInvoicesDto findAll() throws Exception;
	
	FindByIdResInvoicesDto findById(String id) throws Exception;
	
	InsertResDto insert(Invoices data) throws Exception;
	
	UpdateResDto update(Invoices data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
