package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Permissions;

public interface PermissionsDao {

	List<Permissions> findAll() throws Exception;
	
	Permissions findById(String id) throws Exception;
	
	List<Permissions> findAllFilterByName(String input) throws Exception;
	
	Permissions saveOrUpdate(Permissions data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	Permissions findByCode(String code) throws Exception;
}