package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResFilterByNameDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResPemissionsDto;
import com.lawencon.assetsmanagement.dto.permissions.FindByIdResPermissionsDto;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.service.PermissionsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class PermissionsServiceImpl extends BaseServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	public FindAllResPemissionsDto findAll() throws Exception {
		FindAllResPemissionsDto result = new FindAllResPemissionsDto();
		result.setData(permissionsDao.findAll());
		result.setMsg(null);

		return result;
	}

	public FindByIdResPermissionsDto findById(String id) throws Exception {
		FindByIdResPermissionsDto result = new FindByIdResPermissionsDto();
		result.setData(permissionsDao.findById(id));
		result.setMsg(null);

		return result;
	}
	

	@Override
	public FindAllResFilterByNameDto findAllFilterByName(String input) throws Exception {
		FindAllResFilterByNameDto result = new FindAllResFilterByNameDto();
		result.setData(permissionsDao.findAllFilterByName(input));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public InsertResDto insert(Permissions data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();

			begin();
			Permissions permissionsSave = permissionsDao.saveOrUpdate(data);
			commit();

			insertResDataDto.setId(permissionsSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(null);

			return insertResDto;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UpdateResDto update(Permissions data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();

			begin();
			Permissions permissionsUpdate = permissionsDao.saveOrUpdate(data);
			commit();

			updateResDataDto.setVersion(permissionsUpdate.getVersion());
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
			boolean resultDelete = permissionsDao.removeById(id);
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