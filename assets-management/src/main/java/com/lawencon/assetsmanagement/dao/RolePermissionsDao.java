package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.PermissionsDetail;

public interface PermissionsDetailDao {

	List<PermissionsDetail> findAll() throws Exception;
	
	PermissionsDetail findById(String id) throws Exception;
	
	PermissionsDetail saveOrUpdate(PermissionsDetail data) throws Exception;

	boolean removeById(String id) throws Exception;
}
