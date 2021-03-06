package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.constant.ItemTypesCode;
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
		queryBuilder.append("SELECT count(a.id) as total ");
		queryBuilder.append("FROM assets a ");
		queryBuilder.append("INNER JOIN status_assets sa ON a.id_status_asset = sa.id ");
		queryBuilder.append("WHERE sa.code = :code");
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
//		queryBuilder.append("INNER JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.item AS i ");
		queryBuilder.append("LEFT JOIN FETCH i.itemType AS it ");
		
		queryBuilder.append("WHERE it.code = :code ");
		
		String sql = queryBuilder.toString();
		
		return createQuery(sql, Assets.class).setParameter("code", typeCode).getResultList();
	}

	@Override
	public List<Assets> findAllFilterBySearch(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("LEFT JOIN FETCH a.item AS i ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.statusAsset AS sa ");
		queryBuilder.append("WHERE (i.description LIKE '%" + input + "%' OR i.brand LIKE '%" + input + "%') AND sa.code = :statusCode");
		
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, Assets.class)
				.setParameter("statusCode", StatusCode.DEPLOYABLE.getCode())
				.getResultList();
	}
	
	@Override
	public List<Assets> findAllFilterBySearchForGeneralItem(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("LEFT JOIN FETCH a.item AS i ");
		queryBuilder.append("LEFT JOIN FETCH i.itemType ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.statusAsset AS sa ");
		queryBuilder.append("WHERE (i.description LIKE '%" + input + "%' OR i.brand LIKE '%" + input + "%') AND sa.code = :statusCode AND i.itemType.code = :itemTypes ");
		
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, Assets.class)
				.setParameter("statusCode", StatusCode.DEPLOYABLE.getCode())
				.setParameter("itemTypes", ItemTypesCode.GENERAL.getCode())
				.getResultList();
	}

	@Override
	public List<Assets> findAllFilterBySearchForComponent(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("LEFT JOIN FETCH a.item AS i ");
		queryBuilder.append("LEFT JOIN FETCH i.itemType ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.statusAsset AS sa ");
		queryBuilder.append("WHERE (i.description LIKE '%" + input + "%' OR i.brand LIKE '%" + input + "%') AND sa.code = :statusCode AND i.itemType.code = :itemTypes ");
		
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, Assets.class)
				.setParameter("statusCode", StatusCode.DEPLOYABLE.getCode())
				.setParameter("itemTypes", ItemTypesCode.COMPONENT.getCode())
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

	@Override
	public Assets findByCode(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder("");
		queryBuilder.append("SELECT a ");
		queryBuilder.append("FROM Assets a ");
		queryBuilder.append("LEFT JOIN FETCH a.item AS i ");
		queryBuilder.append("LEFT JOIN FETCH a.display ");
		queryBuilder.append("LEFT JOIN FETCH a.statusAsset AS sa ");
		queryBuilder.append("WHERE a.code = :code");
		
		String sql = queryBuilder.toString();
		
		
		return createQuery(sql, Assets.class)
				.setParameter("input", code)
				.getSingleResult();
	}
}
