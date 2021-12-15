package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.UsersService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	public List<Users> findAll() throws Exception {
		return usersDao.findAll();
	}
	
	public Users findById(String id) throws Exception {
		return usersDao.findById(id);
	}

	@Override
	public InsertResDto insert(Users data) throws Exception {
		InsertResDto insertResDto = new InsertResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		
		begin();
		Users usersSave = usersDao.saveOrUpdate(data);
		commit();
		
		insertResDataDto.setId(usersSave.getId());
		insertResDto.setData(insertResDataDto);
		insertResDto.setMsg("....");
		
		return insertResDto;
	}

	@Override
	public UpdateResDto update(Users data) throws Exception {
		UpdateResDto updateResDto = new UpdateResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		
		begin();
		Users usersUpdate = usersDao.saveOrUpdate(data);
		commit();
		
		updateResDataDto.setVersion(usersUpdate.getVersion());
		updateResDto.setData(updateResDataDto);
		updateResDto.setMsg(".....");
		
		return updateResDto;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
		
		begin();
		boolean resultDelete = usersDao.removeById(id);
		commit();
		
		if (resultDelete) {
			deleteResDataDto.setMsg("");
		} else {
			deleteResDataDto.setMsg("");
		}

		return deleteResDataDto;
	}
	
}