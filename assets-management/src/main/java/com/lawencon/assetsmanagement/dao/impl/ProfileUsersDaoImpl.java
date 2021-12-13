package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class ProfileUsersDaoImpl extends BaseDaoImpl<ProfileUsers> implements ProfileUsersDao {

	@Override
	public List<ProfileUsers> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public ProfileUsers findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public ProfileUsers saveOrUpdate(ProfileUsers data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}