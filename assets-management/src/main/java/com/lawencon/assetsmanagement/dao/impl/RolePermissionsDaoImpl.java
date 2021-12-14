package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.PermissionsDetailDao;
import com.lawencon.assetsmanagement.model.PermissionsDetail;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class PermissionsDetailDaoImpl extends BaseDaoImpl<PermissionsDetail> implements PermissionsDetailDao {
	
	@Override
	public List<PermissionsDetail> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public PermissionsDetail findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public PermissionsDetail saveOrUpdate(PermissionsDetail data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}