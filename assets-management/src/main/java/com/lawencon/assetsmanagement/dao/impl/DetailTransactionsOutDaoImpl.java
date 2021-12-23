package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
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
		queryBuilder.append("INNER JOIN FETCH dto.transactionOut AS to ");
		queryBuilder.append("INNER JOIN FETCH dto.asset AS a");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("WHERE dto.to.id = :idHeader ");

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
		queryBuilder.append("INNER JOIN FETCH dti.asset a");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("INNER JOIN FETCH dti.conditionAsset");
		queryBuilder.append("WHERE day(dti.dueDate) - day(current_date()) <= 7 AND dti.statusEmail = false");
		String sql = queryBuilder.toString();
		
		return createQuery(sql, DetailTransactionsOut.class).getResultList();
	}
}