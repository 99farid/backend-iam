package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindByIdResInvoicesDto;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.service.InvoicesService;
import com.lawencon.base.BaseServiceImpl;

@Service
public class InvoicesServiceImpl extends BaseServiceImpl implements InvoicesService{
	@Autowired
	private InvoicesDao invoicesDao;
	
	@Override
	public FindAllResInvoicesDto findAll() throws Exception {
		FindAllResInvoicesDto result = new FindAllResInvoicesDto();
		result.setData(invoicesDao.findAll());
		result.setMsg(null);
		return result;
	}

	@Override
	public FindByIdResInvoicesDto findById(String id) throws Exception {
		FindByIdResInvoicesDto result = new FindByIdResInvoicesDto();
		result.setData( invoicesDao.findById(id));
		result.setMsg(null);
		
		return result;
	}

	@Override
	public InsertResDto insert(Invoices data) throws Exception {
		try {
			begin();
			Invoices invoice = invoicesDao.saveOrUpdate(data);
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(invoice.getId());
			
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
	public UpdateResDto update(Invoices data) throws Exception {
		try {
			begin();
			Invoices invoice = invoicesDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(invoice.getVersion());
			
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
			if (!invoicesDao.removeById(id)) {
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
