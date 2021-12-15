package com.lawencon.assetsmanagement.dao;

import java.util.List;

import com.lawencon.assetsmanagement.model.ProfileUsers;

public interface ProfileUsersDao {

	List<ProfileUsers> findAll() throws Exception;
	
	ProfileUsers findById(String id) throws Exception;
	
	ProfileUsers findByUser(String userId) throws Exception;
	
	ProfileUsers saveOrUpdate(ProfileUsers data) throws Exception;
	
	boolean removeById(String id) throws Exception;
}
