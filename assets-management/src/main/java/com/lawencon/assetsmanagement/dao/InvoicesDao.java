package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Invoices;

public interface InvoicesDao {
	
	List<Invoices> findAll() throws Exception;
	
	Invoices findById(String id) throws Exception;
	
	Invoices saveOrUpdate(Invoices data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	List<Invoices> findAllFilterByCode(String code) throws Exception;
}
