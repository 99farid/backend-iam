package com.lawencon.assetsmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.DetailTransactionsOutDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.DetailTransactionsOut;
import com.lawencon.assetsmanagement.model.TransactionsOut;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class DetailTransactionsOutDaoImpl extends BaseDaoImpl<DetailTransactionsOut> implements DetailTransactionsOutDao {
	
	
	@Override
	public List<DetailTransactionsOut> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public DetailTransactionsOut findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public List<DetailTransactionsOut> findByIdHeader(String idHeader) throws Exception{
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, id_transaction_out, id_asset, check_out_date, ver, created_by, created_date, updated_by, updatedD_date, is_active ");
		queryBuilder.append("FROM transactions_out ");
		queryBuilder.append("WHERE id_transaction_out = :idHeader ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.setParameter("id_transaction_out", idHeader)
				.getResultList();
		List<DetailTransactionsOut> resultDetailTransactionsOut = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
		
			TransactionsOut transactionsOut = new TransactionsOut();
			transactionsOut.setId(objArr[1].toString());
			
			Assets assets = new Assets();
			assets.setId(objArr[2].toString());
			
			DetailTransactionsOut detailTransactionsOut = new DetailTransactionsOut();
			detailTransactionsOut.setId(objArr[0].toString());
			detailTransactionsOut.setTransactionOut(transactionsOut);
			detailTransactionsOut.setAsset(assets);
			
			resultDetailTransactionsOut.add(detailTransactionsOut);
		});
		return resultDetailTransactionsOut;
				
	}
	
	@Override
	public DetailTransactionsOut saveOrUpdate(DetailTransactionsOut data) throws Exception {
		return save(data);
	}
	
}
