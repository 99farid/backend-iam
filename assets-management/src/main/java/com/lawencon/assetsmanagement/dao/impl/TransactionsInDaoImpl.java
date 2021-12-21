package com.lawencon.assetsmanagement.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.TransactionsIn;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class TransactionsInDaoImpl extends BaseDaoImpl<TransactionsIn> implements TransactionsInDao {

	@Override
	public List<TransactionsIn> findAll() throws Exception{
		return getAll();
	}

	@Override
	public TransactionsIn findById(String id) throws Exception{
		return getById(id);
	}

	@Override
	public TransactionsIn saveOrUpdate(TransactionsIn data) throws Exception {
		return save(data);
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SElECT count(id) FROM transactions_in";
		Object result = createNativeQuery(sql).getSingleResult();
				
		return (Integer) result;
	}

	@Override
	public List<TransactionsIn> findAllForPdf() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ti.code, e.full_name AS fullName, to_char(check_in_date,'dd-mm-yyyy') AS checkInDate ");
		queryBuilder.append("FROM transactions_in ti ");
		queryBuilder.append("INNER JOIN transactions_out t ON t.id = ti.id_transaction_out  ");
		queryBuilder.append("INNER JOIN employees e ON e.id = t.id_employee ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
		List<TransactionsIn> resultTransactionIn= new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsIn transactionsIn = new TransactionsIn();
			transactionsIn.setCode(objArr[0].toString());
			
			Employees receiver = new Employees();
			if (objArr[1] != null) {
				receiver.setFullName(objArr[1].toString());
			} else {
				receiver.setFullName("");
			}
			
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setEmployee(receiver);
			
			transactionsIn.setTransactionOut(transactionsOut);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(objArr[2].toString(), formatter);
			transactionsIn.setCheckInDate(localDate);
			
			resultTransactionIn.add(transactionsIn);
		});
		
		return resultTransactionIn;
	}
	
	
	
}
