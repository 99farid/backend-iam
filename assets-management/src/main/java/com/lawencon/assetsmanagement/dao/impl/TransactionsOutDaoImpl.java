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
		queryBuilder.append("SELECT t.id AS id_transout, e.id, e.full_name, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN employees AS e ON e.id = t.id_employee ");
		queryBuilder.append("WHERE t.id_location IS NULL AND t.id_general_item IS NULL ");
		
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

	@Override
	public List<TransactionsOut> findAllFilterByIdLocation() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT t.id AS id_transout, l.id, l.locations_name, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN locations AS l ON l.id = t.id_location ");
		queryBuilder.append("WHERE t.id_employee IS NULL AND t.id_general_item IS NULL ");
		
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

	@Override
	public List<TransactionsOut> findAllFilterByIdGeneralItem() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT t.id AS id_transout, a.id, a.id_item, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN assets AS a ON a.id = t.id_general_item ");
		queryBuilder.append("INNER JOIN items AS i ON i.id = a.id_item ");
		queryBuilder.append("WHERE t.id_employee IS NULL AND t.id_location IS NULL ");
		
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
