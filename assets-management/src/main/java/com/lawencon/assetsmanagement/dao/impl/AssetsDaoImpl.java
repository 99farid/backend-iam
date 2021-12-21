package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.constant.StatusCode;
import com.lawencon.assetsmanagement.dao.AssetsDao;
import com.lawencon.assetsmanagement.model.Assets;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class AssetsDaoImpl extends BaseDaoImpl<Assets> implements AssetsDao{

	@Override
	public List<Assets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Assets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Assets saveOrUpdate(Assets data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public Integer countAsset() throws Exception {
		String sql = "SELECT count(id) as total FROM assets";
		Object result = createNativeQuery(sql).getSingleResult();
		return Integer.valueOf(result.toString());
	}

	@Override
	public Integer countAssetByStatus(String statusCode) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT count(id) as total ");
		queryBuilder.append("FROM assets a ");
		queryBuilder.append("INNER JOIN status_assets ON a.id_status_assets = (SELECT id FROM status_assets WHERE code = :code)");
		String sql = queryBuilder.toString();
		
		Object result = createNativeQuery(sql)
				.setParameter("code", statusCode)
				.getSingleResult();
		
		return Integer.valueOf(result.toString()) ;
	}



	@Override
	public List<Assets> findAllFilterByType(String typeCode) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("INNER JOIN FETCH a.item i ");
		queryBuilder.append("INNER JOIN FETCH i.itemType ");
		queryBuilder.append("WHERE i.itemType.code = :code");
		
		String sql = queryBuilder.toString();
		
		return createQuery(sql, Assets.class).setParameter("code", typeCode).getResultList();
	}

	@Override
	public List<Assets> findAllFilterBySearch(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a");
		queryBuilder.append("FROM Assets a");
		queryBuilder.append("INNER JOIN FETCH a.item i");
		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("WHERE (i.description LIKE :input OR i.brand LIKE :input) AND a.statusAsset.code = :statusCode");
		
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, Assets.class)
				.setParameter("input", input)
				.setParameter("statusCode", StatusCode.DEPLOYABLE.getCode())
				.getResultList();
	}

	@Override
	public List<Assets> findAllForPdf() throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.item i ");
		queryBuilder.append("LEFT JOIN FETCH i.itemType ");
		queryBuilder.append("LEFT JOIN FETCH a.statusAsset ");
		queryBuilder.append("LEFT JOIN FETCH a.company ");
		queryBuilder.append("LEFT JOIN FETCH a.invoice ");
		queryBuilder.append("WHERE a.expiredDate IS NOT NULL ");
	
		String sql = queryBuilder.toString();
		
		return createQuery(sql, Assets.class).getResultList();
	}
}
