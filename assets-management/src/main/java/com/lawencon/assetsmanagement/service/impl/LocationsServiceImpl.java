package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.locations.FindAllResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindByIdResLocationsDto;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.service.LocationsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class LocationsServiceImpl extends BaseServiceImpl implements LocationsService{

	@Autowired
	LocationsDao locationsDao;
	
	@Override
	public FindAllResLocationsDto findAll() throws Exception {
		FindAllResLocationsDto result = new FindAllResLocationsDto();
		result.setData(locationsDao.findAll());
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdResLocationsDto findById(String id) throws Exception {
		FindByIdResLocationsDto result = new FindByIdResLocationsDto();
		result.setData(locationsDao.findById(id));
		result.setMsg(null);
		return result;
	}

	@Override
	public InsertResDto insert(Locations data) throws Exception {
		try {
			begin();
			Locations location = locationsDao.saveOrUpdate(data);
			commit();
			
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(location.getId());
			
			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg("");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public UpdateResDto update(Locations data) throws Exception {
		try {
			begin();
			Locations location = locationsDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(location.getVersion());
			
			UpdateResDto result = new UpdateResDto();
			result.setData(dataResult);
			result.setMsg("");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto result = new DeleteResDataDto();
			begin();
			if (!locationsDao.removeById(id)) {
				result.setMsg("");
			}
			commit();
			result.setMsg("");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

}
