package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Roles;

public interface RolesDao {

	List<Roles> findAll() throws Exception;
	
	Roles findById(String id) throws Exception;
	
	Roles saveOrUpdate(Roles data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	Roles findByCode(String code) throws Exception;
	
}
