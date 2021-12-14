package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Assets;

public interface AssetsDao {
	
	List<Assets> findAll() throws Exception;
	
	Assets findById(String id) throws Exception;
	
	Assets saveOrUpdate(Assets data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
}