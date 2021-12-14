package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.RolesDao;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao {

	@Override
	public List<Roles> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Roles findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Roles saveOrUpdate(Roles data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
