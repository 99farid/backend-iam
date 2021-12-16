package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.InvoicesDao;
import com.lawencon.assetsmanagement.model.Invoices;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class InvoicesDaoImpl extends BaseDaoImpl<Invoices> implements InvoicesDao{

	@Override
	public List<Invoices> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Invoices findById(String id) throws Exception {
		Invoices result = getById(id);
		return result;
	}

	@Override
	public Invoices saveOrUpdate(Invoices data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	
}
