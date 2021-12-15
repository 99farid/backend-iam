package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.model.DetailTransactionsIn;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class DetailTransactionsInDaoImpl extends BaseDaoImpl<DetailTransactionsIn> implements DetailTransactionsInDao{

	@Override
	public List<DetailTransactionsIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public DetailTransactionsIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public DetailTransactionsIn saveOrUpdate(DetailTransactionsIn data) throws Exception {
		return save(data);
	}

	@Override
	public List<DetailTransactionsIn> findByIdHeader(String idHeader) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");		
		queryBuilder.append("SELECT dti ");
		queryBuilder.append("FROM DetailTransactionsIn dti ");
		queryBuilder.append("INNER JOIN FETCH dti.asset a");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item ");
		queryBuilder.append("INNER JOIN FETCH dti.conditionAsset");
		queryBuilder.append("WHERE dti.transactionIn.id = :idHeader");
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, DetailTransactionsIn.class).setParameter("idHeader", idHeader).getResultList();
	}

}
