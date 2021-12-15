package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.constant.StatusCode;
import com.lawencon.assetsmanagement.dao.StatusAssetsDao;
import com.lawencon.assetsmanagement.model.StatusAssets;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class StatusAsstesDaoImpl extends BaseDaoImpl<StatusAssets> implements StatusAssetsDao{

	@Override
	public List<StatusAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public StatusAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusAssets saveOrUpdate(StatusAssets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<StatusAssets> findAllForNewAsset() throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT id, code, status_asset_name, ver, created_by, created_date, updated_by, updated_date, is_active");
		queryBuilder.append("FROM status_assets ");
		queryBuilder.append("WHERE code IN (:deployable , :pending)");
		
		String sql = queryBuilder.toString();
		
		List<?> result = createNativeQuery(sql)
				.setParameter("deployable", "")
				.setParameter("pending", "")		
				.getResultList();
		List<StatusAssets> resultList = new ArrayList<StatusAssets>();
		
		result.forEach(rs->{
			Object[] arrObj = (Object[]) rs;
			StatusAssets status = new StatusAssets();
			status.setId(arrObj[0].toString());
			status.setCode(arrObj[1].toString());
			status.setStatusAssetName(arrObj[2].toString());
			status.setVersion(Long.valueOf(arrObj[3].toString()));
			status.setCreatedBy(arrObj[4].toString());
			status.setCreatedDate(((Timestamp) arrObj[5]).toLocalDateTime());
			if(arrObj[6] != null) {
				status.setUpdatedBy(arrObj[6].toString());
			}
			if(arrObj[7] != null) {
				status.setUpdatedDate(((Timestamp) arrObj[7]).toLocalDateTime());
			}
			status.setIsActive((Boolean) arrObj[8]);
			
			resultList.add(status);
			
		});
		
		return resultList;
	}

	@Override
	public StatusAssets findOnAssignStatus() throws Exception {
		try {
			StringBuilder queryBuilder = new StringBuilder("");
			queryBuilder.append("SELECT id, code, status_asset_name, ver, created_by, created_date, updated_by, updated_date, is_active");
			queryBuilder.append("FROM status_assets ");
			queryBuilder.append("WHERE code = :code");
			
			String sql = queryBuilder.toString();
			Object result = createNativeQuery(sql)
					.setParameter("code", StatusCode.ONASSIGN.getCode())		
					.getSingleResult();
			StatusAssets status = null;
			if(result != null) {
				Object[] arrObj = (Object[]) result;
				status = new StatusAssets();
				status.setId(arrObj[0].toString());
				status.setCode(arrObj[1].toString());
				status.setStatusAssetName(arrObj[2].toString());
				status.setVersion(Long.valueOf(arrObj[3].toString()));
				status.setCreatedBy(arrObj[4].toString());
				status.setCreatedDate(((Timestamp) arrObj[5]).toLocalDateTime());
				if(arrObj[6] != null) {
					status.setUpdatedBy(arrObj[6].toString());
				}
				if(arrObj[7] != null) {
					status.setUpdatedDate(((Timestamp) arrObj[7]).toLocalDateTime());
				}
				status.setIsActive((Boolean) arrObj[8]);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}

}
