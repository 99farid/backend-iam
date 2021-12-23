package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.ConditionAssetsDao;
import com.lawencon.assetsmanagement.model.ConditionAssets;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class ConditionAssetsDaoImpl extends BaseDaoImpl<ConditionAssets> implements ConditionAssetsDao{

	@Override
	public List<ConditionAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public ConditionAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ConditionAssets saveOrUpdate(ConditionAssets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<ConditionAssets> findAllFilterBySearch(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, id_status_asset, condition_asset_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM condition_assets ");
		queryBuilder.append("WHERE code LIKE :input OR condition_asset_name LIKE :input ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).setParameter("input", input).getResultList();
		List<ConditionAssets> resultList = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] arrObj = (Object[]) rs;
			ConditionAssets condition = new ConditionAssets();
			condition.setId(arrObj[0].toString());
			condition.setCode(arrObj[1].toString());
			StatusAssets status = new StatusAssets();
			status.setId(arrObj[2].toString());
			condition.setStatusAsset(status);
			condition.setConditionAssetName(arrObj[3].toString());
			condition.setVersion(Long.valueOf(arrObj[4].toString()));
			condition.setCreatedBy(arrObj[5].toString());
			condition.setCreatedDate(((Timestamp)arrObj[6]).toLocalDateTime());
			if(arrObj[7] != null) {
				condition.setUpdatedBy(arrObj[7].toString());
			}
			if(arrObj[8] != null) {
				condition.setUpdatedDate(((Timestamp)arrObj[8]).toLocalDateTime());
			}
			condition.setIsActive((Boolean) arrObj[9]);
			
			resultList.add(condition);
		});
		return resultList;
	}

	@Override
	public ConditionAssets findByCode(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, id_status_asset, condition_asset_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM condition_assets ");
		queryBuilder.append("WHERE code = :code");
		
		String sql = queryBuilder.toString();
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		ConditionAssets condition = null;
		
		if(result != null) {
			Object[] arrObj = (Object[]) result;
			condition = new ConditionAssets();
			condition.setId(arrObj[0].toString());
			condition.setCode(arrObj[1].toString());
			StatusAssets status = new StatusAssets();
			status.setId(arrObj[2].toString());
			condition.setStatusAsset(status);
			condition.setConditionAssetName(arrObj[3].toString());
			condition.setVersion(Long.valueOf(arrObj[4].toString()));
			condition.setCreatedBy(arrObj[5].toString());
			condition.setCreatedDate(((Timestamp)arrObj[6]).toLocalDateTime());
			if(arrObj[7] != null) {
				condition.setUpdatedBy(arrObj[7].toString());
			}
			if(arrObj[8] != null) {
				condition.setUpdatedDate(((Timestamp)arrObj[8]).toLocalDateTime());
			}
			condition.setIsActive((Boolean) arrObj[9]);
		}
		return condition;
	}
	

}
