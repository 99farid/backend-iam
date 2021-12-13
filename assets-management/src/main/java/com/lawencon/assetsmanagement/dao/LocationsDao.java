package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Locations;

public interface LocationsDao {
	
	List<Locations> findAll() throws Exception;
	
	Locations findById(String id) throws Exception;
	
	Locations saveOrUpdate(Locations data) throws Exception; 
	
	boolean removeById(String id) throws Exception;
	
}
