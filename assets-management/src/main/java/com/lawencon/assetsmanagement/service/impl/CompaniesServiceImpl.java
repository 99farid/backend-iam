package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.service.CompaniesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class CompaniesServiceImpl extends BaseServiceImpl implements CompaniesService {

	@Autowired
	private CompaniesDao companiesDao;

	public List<Companies> findAll() throws Exception {
		return companiesDao.findAll();
	}

	public Companies findById(String id) throws Exception {
		return companiesDao.findById(id);
	}

	@Override
	public InsertResDto insert(Companies data) throws Exception {	
		InsertResDto insertResDto = new InsertResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		
		begin();
		Companies companiesSave = companiesDao.saveOrUpdate(data);
		commit();
		
		insertResDataDto.setId(companiesSave.getId());
		insertResDto.setData(insertResDataDto);
		insertResDto.setMsg(".....");

		return insertResDto;
	}

	@Override
	public UpdateResDto update(Companies data) throws Exception {
		UpdateResDto updateResDto = new UpdateResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();

		begin();
		Companies companiesUpdate = companiesDao.saveOrUpdate(data);
		commit();

		updateResDataDto.setVersion(companiesUpdate.getVersion());
		updateResDto.setData(updateResDataDto);
		updateResDto.setMsg("....");

		return updateResDto;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
		
		begin();
		boolean resultDelete = companiesDao.removeById(id);
		commit();
		
		if (resultDelete) {
			deleteResDataDto.setMsg("");
		} else {
			deleteResDataDto.setMsg("");
		}

		return deleteResDataDto;
	}

}
