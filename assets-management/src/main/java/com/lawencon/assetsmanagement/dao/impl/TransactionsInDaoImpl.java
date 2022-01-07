package com.lawencon.assetsmanagement.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TransactionsInDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.Locations;
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
				
		return Integer.valueOf(result.toString());
	}

	@Override
	public List<TransactionsIn> findAllForPdf() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ti.code, e.full_name AS fullName, ");
		queryBuilder.append("l.locations_name AS locationName, i.description AS itemName, ");
		queryBuilder.append("to_char(check_in_date,'dd-mm-yyyy') AS checkInDate ");
		queryBuilder.append("FROM transactions_in ti ");
		queryBuilder.append("LEFT JOIN transactions_out t ON t.id = ti.id_transaction_out  ");
		queryBuilder.append("LEFT JOIN employees e ON e.id = t.id_employee  ");
		queryBuilder.append("LEFT JOIN locations l ON l.id = t.id_location ");
		queryBuilder.append("LEFT JOIN assets a ON a.id = t.id_general_item ");
		queryBuilder.append("LEFT JOIN items i ON i.id = a.id_item ");
		
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
			
			Locations locations = new Locations();
			if (objArr[2] != null) {
				locations.setLocationName(objArr[2].toString());
			} else {
				locations.setLocationName("");
			}

			Items itemGeneral = new Items();
			if (objArr[3] != null) {
				itemGeneral.setDescription(objArr[3].toString());
			} else {
				itemGeneral.setDescription("");
			}
			
			Assets receiverItem = new Assets();
			receiverItem.setItem(itemGeneral);
			
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setEmployee(receiver);
			transactionsOut.setLocation(locations);
			transactionsOut.setGeneralItem(receiverItem);
			
			transactionsIn.setTransactionOut(transactionsOut);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(objArr[4].toString(), formatter);
			transactionsIn.setCheckInDate(localDate);
			
			resultTransactionIn.add(transactionsIn);
		});
		
		return resultTransactionIn;
	}
}