package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.service.LocationsService;
import com.lawencon.base.BaseServiceImpl;

public class LocationsServiceImpl extends BaseServiceImpl implements LocationsService{

	@Autowired
	LocationsDao locationsDao;
	
	@Override
	public List<Locations> findAll() throws Exception {
		return locationsDao.findAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return locationsDao.findById(id);
	}

	@Override
	public InsertResDto insert(Locations data) throws Exception {
		begin();
		Locations location = locationsDao.saveOrUpdate(data);
		commit();
		
		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(location.getId());
		
		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");
		return result;
	}

	@Override
	public UpdateResDto update(Locations data) throws Exception {
		begin();
		Locations location = locationsDao.saveOrUpdate(data);
		commit();
		
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(location.getVersion());
		
		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");
		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!locationsDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}

}
