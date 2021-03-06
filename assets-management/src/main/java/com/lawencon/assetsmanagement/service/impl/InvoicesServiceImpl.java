package com.lawencon.assetsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.FilesDao;
import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllFilterByCodeResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindAllResInvoicesDto;
import com.lawencon.assetsmanagement.dto.invoices.FindByIdResInvoicesDto;
import com.lawencon.assetsmanagement.model.Files;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.assetsmanagement.service.InvoicesService;

@Service
public class InvoicesServiceImpl extends BaseIamServiceImpl implements InvoicesService{
	@Autowired
	private InvoicesDao invoicesDao;
	
	@Autowired
	private FilesDao filesDao;
	
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
	public InsertResDto insert(Invoices data, MultipartFile invoicePict) throws Exception {
		try {
			data.setCreatedBy(getIdAuth());
			String extention = invoicePict.getOriginalFilename();
			extention = extention.substring(extention.lastIndexOf(".")+1, extention.length());
			
			Files fileInsert = new Files();
			fileInsert.setDataFile(invoicePict.getBytes());
			fileInsert.setExtention(extention);
			fileInsert.setCreatedBy(getIdAuth());
			
			
			begin();
			Files filesSave = new Files();
			filesSave = filesDao.saveOrUpdate(fileInsert);
			data.setInvoicePict(filesSave);
			Invoices invoice = invoicesDao.saveOrUpdate(data);
			commit();
			InsertResDataDto dataResult = new InsertResDataDto();
			dataResult.setId(invoice.getId());
			
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

	@Override
	public UpdateResDto update(Invoices data, MultipartFile invoicePict) throws Exception {
		try {
			data.setUpdatedBy(getIdAuth());
			begin();
			Invoices invoice = invoicesDao.saveOrUpdate(data);
			commit();
			
			UpdateResDataDto dataResult = new UpdateResDataDto();
			dataResult.setVersion(invoice.getVersion());
			
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
			if (!invoicesDao.removeById(id)) {
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

	@Override
	public FindAllFilterByCodeResInvoicesDto findAllFilterByCode(String code) throws Exception {
		FindAllFilterByCodeResInvoicesDto result = new FindAllFilterByCodeResInvoicesDto();
		result.setData(invoicesDao.findAllFilterByCode(code));
		result.setMsg(null);
		return result;
	}

}
