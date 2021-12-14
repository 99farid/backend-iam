package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.RolePermissions;

public interface RolePermissionsDao {

	List<RolePermissions> findAll() throws Exception;
	
	RolePermissions findById(String id) throws Exception;
	
	RolePermissions saveOrUpdate(RolePermissions data) throws Exception;

	boolean removeById(String id) throws Exception;
}
