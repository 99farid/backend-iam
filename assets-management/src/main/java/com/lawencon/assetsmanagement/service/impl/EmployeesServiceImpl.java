package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.employees.FindAllResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByIdResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByResNipDto;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.service.EmployeesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class EmployeesServiceImpl extends BaseServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;
	
	public FindAllResEmployeesDto findAll() throws Exception {
		FindAllResEmployeesDto result = new FindAllResEmployeesDto();
		result.setData(employeesDao.findAll());
		result.setMsg(null);
		
		return result;
	}
	
	public FindByIdResEmployeesDto findById(String id) throws Exception {
		FindByIdResEmployeesDto result = new FindByIdResEmployeesDto();
		result.setData(employeesDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	

	@Override
	public FindByResNipDto findByNip(String nip) throws Exception {
		FindByResNipDto result = new FindByResNipDto();
		result.setData(employeesDao.findByNip(nip));
		result.setMsg(null);
		
		return result;
	}	

	@Override
	public InsertResDto insert(Employees data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();

			begin();
			Employees employee = employeesDao.saveOrUpdate(data);
			commit();
			
			insertResDataDto.setId(employee.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg("....");
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public UpdateResDto update(Employees data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			
			begin();
			Employees employeesUpdate = employeesDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(employeesUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg("....");
			
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
			boolean resultDelete = employeesDao.removeById(id);
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