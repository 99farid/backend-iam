package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.GeneralTemplate;

public interface GeneralTemplateDao {

	List<GeneralTemplate> findAll() throws Exception;
	
	GeneralTemplate findById(String id) throws Exception;
	 
	GeneralTemplate saveOrUpdate(GeneralTemplate data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	GeneralTemplate findByCode(String code) throws Exception;
}
