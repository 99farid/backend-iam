package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.ItemTypes;

public interface ItemTypesDao {
	
	List<ItemTypes> findAll() throws Exception;
	
	ItemTypes findById(String id) throws Exception;
	
	ItemTypes saveOrUpdate(ItemTypes data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	List<ItemTypes> findAllFilterBySearch (String input) throws Exception;
	
	ItemTypes findByCode(String code) throws Exception;
	
}
