package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TransactionsOutDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class TransactionsOutDaoImpl extends BaseDaoImpl<TransactionsOut> implements TransactionsOutDao {

	@Override
	public List<TransactionsOut> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public TransactionsOut findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public TransactionsOut saveOrUpdate(TransactionsOut data) throws Exception {
		return save(data);
	}

	@Override
	public List<TransactionsOut> findAllFilterByIdEmployee() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT to.id AS id_transout, e.id, to.id_employee, e.full_name, ");
		queryBuilder.append("to.ver, to.created_by, to.created_date, to.updated_by, to.updated_date, to.is_active ");
		queryBuilder.append("FROM transactions_out AS to ");
		queryBuilder.append("INNER JOIN employees AS e ON e.id = to.id_employee ");
		queryBuilder.append("WHERE to.id_location IS NULL AND to.id_general_item IS NULL ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[0].toString());
			
			Employees employees = new Employees();
			employees.setId(objArr[1].toString());
			employees.setFullName(objArr[2].toString());
			transactionsOut.setEmployee(employees);
			transactionsOut.setVersion(Long.valueOf(objArr[3].toString()));
			transactionsOut.setCreatedBy(objArr[4].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			if (objArr[5] != null) {
				transactionsOut.setUpdatedBy(objArr[5].toString());
			}
			if (objArr[6] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[7].toString()));
			
			resultTransactionOut.add(transactionsOut);
		});
				
		return resultTransactionOut;
	}

	@Override
	public List<TransactionsOut> findAllFilterByIdLocation() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT to.id AS id_transout, l.id, to.id_location, l.location_name, ");
		queryBuilder.append("to.ver, to.created_by, to.created_date, to.updated_by, to.updated_date, to.is_active ");
		queryBuilder.append("FROM transactions_out AS to ");
		queryBuilder.append("INNER JOIN locations AS l ON l.id = to.id_location ");
		queryBuilder.append("WHERE to.id_employee IS NULL AND to.id_general_item IS NULL ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[0].toString());
			
			Locations locations = new Locations();
			locations.setId(objArr[1].toString());
			locations.setLocationName(objArr[2].toString());
			transactionsOut.setLocation(locations);
			transactionsOut.setVersion(Long.valueOf(objArr[3].toString()));
			transactionsOut.setCreatedBy(objArr[4].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			if (objArr[5] != null) {
				transactionsOut.setUpdatedBy(objArr[5].toString());
			}
			if (objArr[6] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[7].toString()));
			
			resultTransactionOut.add(transactionsOut);
		});
				
		return resultTransactionOut;
	}

	@Override
	public List<TransactionsOut> findAllFilterByIdGeneralItem() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT to.id AS id_transout, a.id, to.id_general_item, a.id_item, ");
		queryBuilder.append("to.ver, to.created_by, to.created_date, to.updated_by, to.updated_date, to.is_active ");
		queryBuilder.append("FROM transactions_out AS to ");
		queryBuilder.append("INNER JOIN assets AS a ON a.id = to.id_general_item ");
		queryBuilder.append("INNER JOIN items AS i ON i.id = a.id_item ");
		queryBuilder.append("WHERE to.id_employee IS NULL AND to.id_location IS NULL ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[0].toString());
			
			Assets receiverItem = new Assets();
			receiverItem.setId(objArr[1].toString());
			
			Items itemGeneral = new Items();
			itemGeneral.setDescription(objArr[2].toString());
			receiverItem.setItem(itemGeneral);
			
			transactionsOut.setGeneralItem(receiverItem);
			transactionsOut.setVersion(Long.valueOf(objArr[3].toString()));
			transactionsOut.setCreatedBy(objArr[4].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			if (objArr[6] != null) {
				transactionsOut.setUpdatedBy(objArr[6].toString());
			}
			if (objArr[7] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[7].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[8].toString()));
			
			resultTransactionOut.add(transactionsOut);
		});
				
		return resultTransactionOut;
	}
	
}
