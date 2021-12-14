package com.lawencon.assetsmanagement.service;

import java.util.List;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Users;

public interface UsersService {

	List<Users> findAll() throws Exception;
	
	Users findById(String id) throws Exception;
	
	InsertResDto insert(Users data) throws Exception;
	
	UpdateResDto update(Users data) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
}
