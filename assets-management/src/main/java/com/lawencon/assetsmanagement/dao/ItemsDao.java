package com.lawencon.assetsmanagement.dao;

import java.math.BigDecimal;
import java.util.List;

import com.lawencon.assetsmanagement.model.Items;

public interface ItemsDao {
	
	List<Items> findAll() throws Exception;
	
	Items findById(String id) throws Exception;
	
	Items saveOrUpdate (Items data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
	BigDecimal getTotalPrice() throws Exception;
}
