package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResRolePermissionsDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindByIdResRolePermissionsDto;
import com.lawencon.assetsmanagement.service.RolePermissionsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class RolePermissionsServiceImpl extends BaseServiceImpl implements RolePermissionsService {

	@Autowired
	private RolePermissionsDao permissionsDetailDao;
	
	@Override
	public FindAllResRolePermissionsDto findAll() throws Exception {
		FindAllResRolePermissionsDto result = new FindAllResRolePermissionsDto();
		result.setData(permissionsDetailDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResRolePermissionsDto findById(String id) throws Exception {
		FindByIdResRolePermissionsDto result = new FindByIdResRolePermissionsDto();
		result.setData(permissionsDetailDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

//	@Override
//	public UpdateResDto update(RolePermissions data) throws Exception {
//		UpdateResDto updateResDto = new UpdateResDto();
//		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
//		
//		return updateResDto;
//	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
			
			begin();
			boolean resultDelete = permissionsDetailDao.removeById(id);
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
