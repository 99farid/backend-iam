package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

	@Override
	public List<Users> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Users findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Users saveOrUpdate(Users data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	
}