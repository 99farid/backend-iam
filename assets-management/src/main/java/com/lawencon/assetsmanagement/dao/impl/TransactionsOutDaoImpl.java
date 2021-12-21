package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		queryBuilder.append("SELECT t.id AS id_transout, e.id, e.full_name, t.check_out_date, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN employees AS e ON e.id = t.id_employee ");
		queryBuilder.append("WHERE t.id_location IS NULL AND t.id_general_item IS NULL ");

		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();

		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[0].toString());

			Employees employees = new Employees();
			employees.setId(objArr[1].toString());
			employees.setFullName(objArr[2].toString());
			transactionsOut.setEmployee(employees);
		
			transactionsOut.setCheckOutDate(((Date)objArr[3]).toLocalDate());
			transactionsOut.setVersion(Long.valueOf(objArr[4].toString()));
			transactionsOut.setCreatedBy(objArr[5].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			if (objArr[7] != null) {
				transactionsOut.setUpdatedBy(objArr[7].toString());
			}
			if (objArr[8] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[9].toString()));

			resultTransactionOut.add(transactionsOut);
		});

		return resultTransactionOut;
	}

	@Override
	public List<TransactionsOut> findAllFilterByIdLocation() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT t.id AS id_transout, l.id, l.locations_name, t.check_out_date, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN locations AS l ON l.id = t.id_location ");
		queryBuilder.append("WHERE t.id_employee IS NULL AND t.id_general_item IS NULL ");

		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();

		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[0].toString());

			Locations locations = new Locations();
			locations.setId(objArr[1].toString());
			locations.setLocationName(objArr[2].toString());
			transactionsOut.setLocation(locations);
			
			transactionsOut.setCheckOutDate(((Date)objArr[3]).toLocalDate());
			transactionsOut.setVersion(Long.valueOf(objArr[4].toString()));
			transactionsOut.setCreatedBy(objArr[5].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			if (objArr[7] != null) {
				transactionsOut.setUpdatedBy(objArr[7].toString());
			}
			if (objArr[8] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[9].toString()));

			resultTransactionOut.add(transactionsOut);
		});

		return resultTransactionOut;
	}

	@Override
	public List<TransactionsOut> findAllFilterByIdGeneralItem() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT t.id AS id_transout, a.id, a.id_item, t.check_out_date, ");
		queryBuilder.append("t.ver, t.created_by, t.created_date, t.updated_by, t.updated_date, t.is_active ");
		queryBuilder.append("FROM transactions_out AS t ");
		queryBuilder.append("INNER JOIN assets AS a ON a.id = t.id_general_item ");
		queryBuilder.append("INNER JOIN items AS i ON i.id = a.id_item ");
		queryBuilder.append("WHERE t.id_employee IS NULL AND t.id_location IS NULL ");

		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
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
			
			transactionsOut.setCheckOutDate(((Date)objArr[3]).toLocalDate());
			transactionsOut.setVersion(Long.valueOf(objArr[4].toString()));
			transactionsOut.setCreatedBy(objArr[5].toString());
			transactionsOut.setCreatedDate(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			if (objArr[7] != null) {
				transactionsOut.setUpdatedBy(objArr[7].toString());
			}
			if (objArr[8] != null) {
				transactionsOut.setUpdatedDate(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
			}
			transactionsOut.setIsActive(Boolean.valueOf(objArr[9].toString()));

			resultTransactionOut.add(transactionsOut);
		});

		return resultTransactionOut;
	}

	@Override
	public List<TransactionsOut> findAllForPdf() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT t.code, e.full_name AS fullName, ");
		queryBuilder.append("l.locations_name AS locationName, i.description AS itemName, ");
		queryBuilder.append("to_char(check_out_date,'dd-mm-yyyy') AS checkOutDate ");
		queryBuilder.append("FROM transactions_out t ");
		queryBuilder.append("LEFT JOIN employees e ON e.id = t.id_employee  ");
		queryBuilder.append("LEFT JOIN locations l ON l.id = t.id_location ");
		queryBuilder.append("LEFT JOIN assets a ON a.id = t.id_general_item ");
		queryBuilder.append("LEFT JOIN items i ON i.id = a.id_item ");

		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
		List<TransactionsOut> resultTransactionOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setCode(objArr[0].toString());

			Employees employees = new Employees();
			if (objArr[1] != null) {
				employees.setFullName(objArr[1].toString());
			} else {
				employees.setFullName("");
			}
			transactionsOut.setEmployee(employees);

			Locations locations = new Locations();
			if (objArr[2] != null) {
				locations.setLocationName(objArr[2].toString());
			} else {
				locations.setLocationName("");
			}
			transactionsOut.setLocation(locations);

			Items itemGeneral = new Items();
			if (objArr[3] != null) {
				itemGeneral.setDescription(objArr[3].toString());
			} else {
				itemGeneral.setDescription("");
			}
			
			Assets receiverItem = new Assets();
			receiverItem.setItem(itemGeneral);
			transactionsOut.setGeneralItem(receiverItem);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(objArr[4].toString(), formatter);
			transactionsOut.setCheckOutDate(localDate);

			resultTransactionOut.add(transactionsOut);
		});
		
		return resultTransactionOut;
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT count(id) FROM transactions_out";
		Object result = createNativeQuery(sql).getSingleResult();
				
		return Integer.valueOf(result.toString());
	}
	
	
}
