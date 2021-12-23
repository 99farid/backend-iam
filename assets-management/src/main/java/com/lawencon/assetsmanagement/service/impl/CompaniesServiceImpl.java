package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.companies.FindAllResCompaniesDto;
import com.lawencon.assetsmanagement.dto.companies.FindByIdResCompaniesDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.service.CompaniesService;

@Service
public class CompaniesServiceImpl extends BaseIamServiceImpl implements CompaniesService {

	@Autowired
	private CompaniesDao companiesDao;

	public FindAllResCompaniesDto findAll() throws Exception {
		FindAllResCompaniesDto result = new FindAllResCompaniesDto();
		result.setData(companiesDao.findAll());
		result.setMsg(null);
	
		return result;
	}

	public FindByIdResCompaniesDto findById(String id) throws Exception {
		FindByIdResCompaniesDto result = new FindByIdResCompaniesDto();
		result.setData(companiesDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	private void validationInsert(Companies data) throws Exception{
		if(data != null) {
			if(data.getCode() != null) {
				Companies company = companiesDao.findByCode(data.getCode());
				if(company != null) {
					throw new ValidationIamException("Code already Used");
				}
			}else {
				throw new ValidationIamException("Data is not complete");
			}
			if(data.getCompanyName() == null) {
				throw new ValidationIamException("Data is not complate");
			}
		}else {
			throw new ValidationIamException("Data is not found");
		}
	}
	@Override
	public InsertResDto insert(Companies data) throws Exception {	
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			validationInsert(data);
			data.setCreatedBy(getIdAuth());
			begin();
			Companies companiesSave = companiesDao.saveOrUpdate(data);
			commit();
			
			insertResDataDto.setId(companiesSave.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());

			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	private void validationUpdate(Companies data) throws Exception{
		if(data != null) {
			if(data.getId() != null) {
				Companies company = companiesDao.findById(data.getId());
				if(company == null) {
					throw new ValidationIamException("Data not found");
				}
			}else {
				throw new ValidationIamException("Data not found");
			}
			if(data.getCode() == null || data.getCompanyName() == null || data.getCreatedBy() == null || data.getCreatedDate() == null) {
				throw new ValidationIamException("Data not complete");
			}
				
		}else {
			throw new ValidationIamException("Data not found");
		}
	}

	@Override
	public UpdateResDto update(Companies data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			validationUpdate(data);
			data.setUpdatedBy(getIdAuth());
			begin();
			Companies companiesUpdate = companiesDao.saveOrUpdate(data);
			commit();

			updateResDataDto.setVersion(companiesUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());

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
			boolean resultDelete = companiesDao.removeById(id);
			commit();
			
			if (resultDelete) {
				deleteResDataDto.setMsg(ResponseMsg.SUCCESS_DELETE.getMsg());
			} else {
				deleteResDataDto.setMsg(ResponseMsg.FAILED_DELETE.getMsg());
			}

			return deleteResDataDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
