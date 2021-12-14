package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.StatusAssets;

public interface StatusAssetsDao {
	
 	List<StatusAssets> findAll() throws Exception;
 	
 	StatusAssets findById(String id) throws Exception;
 	
 	StatusAssets saveOrUpdate(StatusAssets data) throws Exception;
 	
 	boolean removeById(String id) throws Exception;
}
