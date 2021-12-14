package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.service.FilesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class FilesServiceImpl extends BaseServiceImpl implements FilesService{
	@Autowired
	private FilesDao filesDao;
	@Override
	public Files findById(String id) throws Exception {
		return filesDao.findById(id);
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!filesDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}
	
}
