package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class RolePermissionsDaoImpl extends BaseDaoImpl<RolePermissions> implements RolePermissionsDao {
	
	@Override
	public List<RolePermissions> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public RolePermissions findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public RolePermissions saveOrUpdate(RolePermissions data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}