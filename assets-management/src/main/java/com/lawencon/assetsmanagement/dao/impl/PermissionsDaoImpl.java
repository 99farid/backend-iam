package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class PermissionsDaoImpl extends BaseDaoImpl<Permissions> implements PermissionsDao {
	
	@Override
	public List<Permissions> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Permissions findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Permissions saveOrUpdate(Permissions data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
