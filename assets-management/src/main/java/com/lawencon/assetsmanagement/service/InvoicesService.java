package com.lawencon.assetsmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllFilterByCodeResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindByIdResInvoicesDto;
import com.lawencon.assetsmanagement.model.Invoices;

public interface InvoicesService {
	FindAllResInvoicesDto findAll() throws Exception;
	
	FindByIdResInvoicesDto findById(String id) throws Exception;
	
	InsertResDto insert(Invoices data, MultipartFile invoicePict) throws Exception;
	
	UpdateResDto update(Invoices data, MultipartFile invoicePict) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
	FindAllFilterByCodeResInvoicesDto findAllFilterByCode (String code) throws Exception;
}
