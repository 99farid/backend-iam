package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindAllResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByIdResProfileUsersDto;
import com.lawencon.assetsmanagement.dto.profileusers.FindByResUserIdDto;
import com.lawencon.assetsmanagement.dto.profileusers.InsertReqDataProfileUsersDto;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.assetsmanagement.service.ProfileUsersService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class ProfileUsersServiceImpl extends BaseServiceImpl implements ProfileUsersService {

	@Autowired
	private ProfileUsersDao profileUsersDao;
	
	@Autowired
	private FilesDao filesDao;
	
	public FindAllResProfileUsersDto findAll() throws Exception {
		FindAllResProfileUsersDto result = new FindAllResProfileUsersDto();
		result.setData(profileUsersDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResProfileUsersDto findById(String id) throws Exception {
		FindByIdResProfileUsersDto result = new FindByIdResProfileUsersDto();
		result.setData(profileUsersDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	
	@Override
	public FindByResUserIdDto findByUser(String userId) throws Exception {
		FindByResUserIdDto result = new FindByResUserIdDto();
		result.setData(profileUsersDao.findByUser(userId));
		result.setMsg(null);
		
		return result;
	}
	

	@Override
	public InsertResDto insert(InsertReqDataProfileUsersDto data, MultipartFile file) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			
			String extention = file.getName();
			extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
			
			Files fileInsert = new Files();
			fileInsert.setDataFile(file.getBytes());
			fileInsert.setExtention(extention);
			
			begin();
			Files filesSave = new Files();
			filesSave = filesDao.saveOrUpdate(fileInsert);
			ProfileUsers profileUsers = new ProfileUsers();
			
			profileUsers.setProfilePict(filesSave);
			profileUsers.setCreatedBy("....");
			profileUsers.setIsActive(data.getIsActive());
			
			ProfileUsers profileUsersSave = profileUsersDao.saveOrUpdate(profileUsers);
			commit();
			
			insertResDataDto.setId(profileUsersSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(".....");
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UpdateResDto update(ProfileUsers data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			
			begin();
			ProfileUsers profileUsersUpdate = profileUsersDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(profileUsersUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg("....");
			
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
			boolean resultDelete = profileUsersDao.removeById(id);
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