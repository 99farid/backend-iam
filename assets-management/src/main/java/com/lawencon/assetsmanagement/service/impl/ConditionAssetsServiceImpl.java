package com.lawencon.assetsmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindAllResConditionAssetsDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindByIdResConditionAssetsDto;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.service.ConditionAssetsService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class ConditionAssetsServiceImpl extends BaseServiceImpl implements ConditionAssetsService{
	private ConditionAssetsDao conditionAssetsDao;

	@Override
	public FindAllResConditionAssetsDto findAll() throws Exception {
		FindAllResConditionAssetsDto result = new FindAllResConditionAssetsDto();
		result.setData(conditionAssetsDao.findAll());
		result.setMsg(null);
		
		return result;
	}

	@Override
	public FindByIdResConditionAssetsDto findById(String id) throws Exception {
		FindByIdResConditionAssetsDto result = new FindByIdResConditionAssetsDto();
		result.setData(conditionAssetsDao.findById(id));
		result.setMsg(null);
		
		return result;
	}	

	@Override
	public InsertResDto insert(ConditionAssets data) throws Exception {
		try {
			begin();
			ConditionAssets condition = conditionAssetsDao.saveOrUpdate(data);
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(condition.getId());
			
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
	public UpdateResDto update(ConditionAssets data) throws Exception {
		try {
			begin();
			ConditionAssets condition = conditionAssetsDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(condition.getVersion());
			
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
			if (!conditionAssetsDao.removeById(id)) {
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
