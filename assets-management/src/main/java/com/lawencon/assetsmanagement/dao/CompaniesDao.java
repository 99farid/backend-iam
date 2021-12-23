package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Companies;

public interface CompaniesDao {

	List<Companies> findAll() throws Exception;
	
	Companies findById(String id) throws Exception;
	
	Companies saveOrUpdate(Companies data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	List<Companies> findAllFilterBySearch (String input) throws Exception;
	
	Companies findByCode (String code) throws Exception;
	
}