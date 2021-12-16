package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.users.FindAllResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByIdResUsersDto;
import com.lawencon.assetsmanagement.dto.users.FindByResEmailDto;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.service.UsersService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	public FindAllResUsersDto findAll() throws Exception {
		FindAllResUsersDto result = new FindAllResUsersDto();
		result.setData(usersDao.findAll());
		result.setMsg(null);
		
		return result;
	}
	
	public FindByIdResUsersDto findById(String id) throws Exception {
		FindByIdResUsersDto result = new FindByIdResUsersDto();
		result.setData(usersDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByResEmailDto findByEmail(String email) throws Exception {
		FindByResEmailDto result = new FindByResEmailDto();
		result.setData(usersDao.findByEmail(email));
		result.setMsg(null);
		
		return result;
	}
	
	@Override
	public InsertResDto insert(Users data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			
			begin();
			Users usersSave = usersDao.saveOrUpdate(data);
			commit();
			
			insertResDataDto.setId(usersSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg("....");
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UpdateResDto update(Users data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			
			begin();
			Users usersUpdate = usersDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(usersUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(".....");
			
			return updateResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
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
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}