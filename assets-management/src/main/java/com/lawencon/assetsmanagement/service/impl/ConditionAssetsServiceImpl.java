package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindAllResConditionAssetsDto;
import com.lawencon.assetsmanagement.dto.conditionassets.FindByIdResConditionAssetsDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.service.ConditionAssetsService;

@Service
public class ConditionAssetsServiceImpl extends BaseIamServiceImpl implements ConditionAssetsService{
	
	@Autowired
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
	private void validationInsert(ConditionAssets data) throws Exception{
		if(data != null) {
			if(data.getCode() != null) {
				ConditionAssets condition = conditionAssetsDao.findByCode(data.getCode());
				if(condition != null) {
					throw new ValidationIamException("Code already Used");
				}
			}else {
				throw new ValidationIamException("Data is not complete");
			}
			if(data.getConditionAssetName() == null || data.getStatusAsset() == null) {
				throw new ValidationIamException("Data is not complate");
			}
		}else {
			throw new ValidationIamException("Data is not found");
		}
	}
	@Override
	public InsertResDto insert(ConditionAssets data) throws Exception {
		try {
			validationInsert(data);
			data.setCreatedBy(getIdAuth());
			begin();
			ConditionAssets condition = conditionAssetsDao.saveOrUpdate(data);
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(condition.getId());
			
			InsertResDto result = new InsertResDto();
			result.setData(dataResult);
			result.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}
	private void validationUpdate(ConditionAssets data) throws Exception{
		if(data != null) {
			if(data.getId() != null) {
				ConditionAssets condition = conditionAssetsDao.findById(data.getId());
				if(condition == null) {
					throw new ValidationIamException("Data not found");
				}
			}else {
				throw new ValidationIamException("Data not found");
			}
			if(data.getCode() == null || data.getConditionAssetName() == null || data.getCreatedBy() == null || data.getCreatedDate() == null) {
				throw new ValidationIamException("Data not complete");
			}
				
		}else {
			throw new ValidationIamException("Data not found");
		}
	}
	@Override
	public UpdateResDto update(ConditionAssets data) throws Exception {
		try {
			validationUpdate(data);
			data.setUpdatedBy(getIdAuth());
			begin();
			ConditionAssets condition = conditionAssetsDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(condition.getVersion());
			
			UpdateResDto result = new UpdateResDto();
			result.setData(dataResult);
			result.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());
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
				result.setMsg(ResponseMsg.FAILED_DELETE.getMsg());
			}
			commit();
			result.setMsg(ResponseMsg.SUCCESS_DELETE.getMsg());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}

}
