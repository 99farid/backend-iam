package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.service.InvoicesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class InvoicesServiceImpl extends BaseServiceImpl implements InvoicesService{
	@Autowired
	private InvoicesDao invoicesDao;
	
	@Override
	public List<Invoices> findAll() throws Exception {
		return invoicesDao.findAll();
	}

	@Override
	public Invoices findById(String id) throws Exception {
		return invoicesDao.findById(id);
	}

	@Override
	public InsertResDto insert(Invoices data) throws Exception {
		begin();
		Invoices invoice = invoicesDao.saveOrUpdate(data);
		commit();
		InsertResDataDto dataResult = new InsertResDataDto();
		dataResult.setId(invoice.getId());
		
		InsertResDto result = new InsertResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public UpdateResDto update(Invoices data) throws Exception {
		begin();
		Invoices invoice = invoicesDao.saveOrUpdate(data);
		commit();
		
		UpdateResDataDto dataResult = new UpdateResDataDto();
		dataResult.setVersion(invoice.getVersion());
		
		UpdateResDto result = new UpdateResDto();
		result.setData(dataResult);
		result.setMsg("");
		
		return result;
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		DeleteResDataDto result = new DeleteResDataDto();
		begin();
		if (!invoicesDao.removeById(id)) {
			result.setMsg("");
		}
		commit();
		result.setMsg("");
		return result;
	}

}
