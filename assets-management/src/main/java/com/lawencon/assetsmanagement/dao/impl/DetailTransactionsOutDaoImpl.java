package com.lawencon.assetsmanagement.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.constant.StatusCode;
import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.Items;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class DetailTransactionsOutDaoImpl extends BaseDaoImpl<DetailTransactionsOut>
		implements DetailTransactionsOutDao {

	@Override
	public List<DetailTransactionsOut> findAll() throws Exception {
		return getAll();
	}

	@Override
	public DetailTransactionsOut findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<DetailTransactionsOut> findByIdHeader(String idHeader) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT dto FROM DetailTransactionsOut AS dto ");
		queryBuilder.append("INNER JOIN FETCH dto.transactionOut ");
		queryBuilder.append("INNER JOIN FETCH dto.asset AS a ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("WHERE dto.transactionOut.id = :idHeader ");

		String sql = queryBuilder.toString();

		return createQuery(sql, DetailTransactionsOut.class)
				.setParameter("idHeader", idHeader)
				.getResultList();
	}

	@Override
	public DetailTransactionsOut saveOrUpdate(DetailTransactionsOut data) throws Exception {
		return save(data);
	}

	@Override
	public List<DetailTransactionsOut> findAllForDueReport() throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");		
		queryBuilder.append("SELECT dti ");
		queryBuilder.append("FROM DetailTransactionsOut dti ");
		queryBuilder.append("INNER JOIN FETCH dti.asset a ");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("WHERE day(dti.dueDate) - day(current_date()) <= 7 AND dti.statusEmail = false");
		String sql = queryBuilder.toString();
		
		return createQuery(sql, DetailTransactionsOut.class).getResultList();
	}
	public List<DetailTransactionsOut> findAllForPdf() throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT tro.code, i.description, e.full_name,  ");
		queryBuilder.append("l.locations_name AS locationName, to_char(dto.due_date,'dd-mm-yyyy') AS dueDate ");
		queryBuilder.append("FROM detail_transactions_out dto ");
		queryBuilder.append("LEFT JOIN transactions_out tro  ON tro.id = dto.id_transaction_out ");
		queryBuilder.append("LEFT JOIN assets a ON a.id = tro.id_general_item ");
		queryBuilder.append("LEFT JOIN items i ON i.id = a.id_item  ");
		queryBuilder.append("LEFT JOIN employees e ON e.id = tro.id_employee ");
		queryBuilder.append("LEFT JOIN locations l ON l.id = tro.id_location ");
		queryBuilder.append("WHERE dto.due_date IS NOT NULL AND dto.due_date <= DATE(NOW()) ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).getResultList();
		List<DetailTransactionsOut> resultDetailTransactionOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			DetailTransactionsOut detailTransactionsOut = new DetailTransactionsOut();
			
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setCode(objArr[0].toString());
			
			Items itemGeneral = new Items();
			if (objArr[1] != null) {
				itemGeneral.setDescription(objArr[1].toString());
			} else {
				itemGeneral.setDescription("");
			}
			
			Assets receiverItem = new Assets();
			receiverItem.setItem(itemGeneral);
			transactionsOut.setGeneralItem(receiverItem);
			
			Employees employees = new Employees();
			if (objArr[2] != null) {
				employees.setFullName(objArr[2].toString());
			} else {
				employees.setFullName("");
			}
			transactionsOut.setEmployee(employees);

			Locations locations = new Locations();
			if (objArr[3] != null) {
				locations.setLocationName(objArr[3].toString());
			} else {
				locations.setLocationName("");
			}
			transactionsOut.setLocation(locations);			
			
			detailTransactionsOut.setTransactionOut(transactionsOut);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate dueDate = LocalDate.parse(objArr[4].toString(), formatter);
			detailTransactionsOut.setDueDate(dueDate);

			resultDetailTransactionOut.add(detailTransactionsOut);
		});
		
		return resultDetailTransactionOut;
	}

	@Override
	public List<DetailTransactionsOut> findByIdHeaderForCheckIn(String idHeader) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT dto FROM DetailTransactionsOut AS dto ");
		queryBuilder.append("INNER JOIN FETCH dto.transactionOut ");
		queryBuilder.append("INNER JOIN FETCH dto.asset AS a ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("WHERE dto.transactionOut.id = :idHeader AND a.statusAsset.code = :statusCode");

		String sql = queryBuilder.toString();

		return createQuery(sql, DetailTransactionsOut.class)
				.setParameter("idHeader", idHeader)
				.setParameter("statusCode", StatusCode.ONASSIGN)
				.getResultList();
	}
}