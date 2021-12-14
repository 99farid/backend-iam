package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.DetailTransactionsInDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.model.DetailTransactionsIn;
import com.lawencon.assetsmanagement.model.TransactionsIn;
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
		queryBuilder.append("SELECT id, id_transaction_in, id_asset, id_condition_asset, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM detail_transactions_in ");
		queryBuilder.append("WHERE id_transcations_in = :idHeader");
		
		String sql = queryBuilder.toString();
		
		List<?> result = createNativeQuery(sql)
				.setParameter("idHeader", idHeader)
				.getResultList();
		
		List<DetailTransactionsIn> resultList = new ArrayList<DetailTransactionsIn>();
		
		result.forEach(rs -> {
			Object[] arrObj = (Object[]) rs;
			DetailTransactionsIn detail = new DetailTransactionsIn();
			detail.setId(arrObj[0].toString());
			
			TransactionsIn header = new TransactionsIn();
			header.setId(arrObj[1].toString());
			detail.setTransactionIn(header);
			
			Assets asset = new Assets();
			asset.setId(arrObj[2].toString());
			detail.setAsset(asset);
			
			ConditionAssets condition = new ConditionAssets();
			condition.setId(arrObj[3].toString());
			detail.setConditionAsset(condition);
			
			detail.setVersion(Long.valueOf(arrObj[4].toString()));
			detail.setCreatedBy(arrObj[5].toString());
			detail.setCreatedDate(((Timestamp) arrObj[6]).toLocalDateTime());
			if(arrObj[7] != null) {
				detail.setUpdatedBy(arrObj[7].toString());
			}
			if(arrObj[8] != null) {
				detail.setUpdatedDate(((Timestamp) arrObj[8]).toLocalDateTime());
			}
			detail.setIsActive((Boolean) arrObj[9]);
	
			resultList.add(detail);
		});
		
		return resultList;
	}

}
