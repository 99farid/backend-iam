package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.assetsmanagement.service.StatusAssetsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class StatusAssetsServiceImpl extends BaseServiceImpl implements StatusAssetsService {
	
	@Autowired
	StatusAssetsDao statusAssetsDao;
	
	@Override
	public List<StatusAssets> findAll() throws Exception {
		return statusAssetsDao.findAll();
	}

	@Override
	public StatusAssets findById(String id) throws Exception {
		return statusAssetsDao.findById(id);
	}

	@Override
	public InsertResDto insert(StatusAssets data) throws Exception {
		begin();
		StatusAssets status = statusAssetsDao.saveOrUpdate(data);
		commit();
		
		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(status.getId());
		
		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");
		return result;
	}

	@Override
	public UpdateResDto update(StatusAssets data) throws Exception {
		begin();
		StatusAssets status = statusAssetsDao.saveOrUpdate(data);
		commit();
		
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(status.getVersion());
		
		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");
		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!statusAssetsDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}

}
