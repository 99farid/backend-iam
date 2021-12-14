package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.service.EmployeesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class EmployeesServiceImpl extends BaseServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;
	
	public List<Employees> findAll() throws Exception {
		return employeesDao.findAll();
	}
	
	public Employees findById(String id) throws Exception {
		return employeesDao.findById(id);
	}

	@Override
	public InsertResDto insert(Employees data) throws Exception {
		InsertResDto insertResDto = new InsertResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();

		begin();
		Employees employee = employeesDao.saveOrUpdate(data);
		commit();
		
		insertResDataDto.setId(employee.getId());
		insertResDto.setData(insertResDataDto);
		insertResDto.setMsg("....");
		
		return insertResDto;
	}

	@Override
	public UpdateResDto update(Employees data) throws Exception {
		UpdateResDto updateResDto = new UpdateResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		
		begin();
		Employees employeesUpdate = employeesDao.saveOrUpdate(data);
		commit();
		
		updateResDataDto.setVersion(employeesUpdate.getVersion());
		updateResDto.setData(updateResDataDto);
		updateResDto.setMsg("....");
		
		return updateResDto;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
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
	}
	
	
}
