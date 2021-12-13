package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Files;

public interface FilesDao {

	List<Files> findAll() throws Exception;
	
	Files findById(String id) throws Exception;
	 
	Files saveOrUpdate(Files data) throws Exception;
	
	boolean removeById(String id) throws Exception;
}
