package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Invoices;

import java.util.List;

public interface InvoicesService {
	List<Invoices> findAll() throws Exception;
	
	Invoices findById(String id) throws Exception;
	
	InsertResDto insert(Invoices data) throws Exception;
	
	UpdateResDto update(Invoices data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
