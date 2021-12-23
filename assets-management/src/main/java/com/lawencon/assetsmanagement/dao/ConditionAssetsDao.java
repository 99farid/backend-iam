package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.ConditionAssets;


public interface ConditionAssetsDao {
	
	List<ConditionAssets> findAll() throws Exception;
	
	ConditionAssets findById(String id) throws Exception;
	
	ConditionAssets saveOrUpdate(ConditionAssets data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	List<ConditionAssets> findAllFilterBySearch(String input) throws Exception;
	
	ConditionAssets findByCode(String code) throws Exception;
}
