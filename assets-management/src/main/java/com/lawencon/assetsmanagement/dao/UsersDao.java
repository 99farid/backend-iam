package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.Users;

public interface UsersDao {

	List<Users> findAll() throws Exception;
	
	Users findById(String id) throws Exception;
	
	Users saveOrUpdate(Users data) throws Exception;
	
	boolean removeById(String id) throws Exception;
	
//	Users getByUsername (String email) throws Exception;
}