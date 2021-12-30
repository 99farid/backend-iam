package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.LocationsDao;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class LocationsDaoImpl extends BaseDaoImpl<Locations> implements LocationsDao{

	@Override
	public List<Locations> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Locations saveOrUpdate(Locations data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public Locations findByCode(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, id_company, code, locations_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM locations ");
		queryBuilder.append("WHERE code = :code");
		
		String sql = queryBuilder.toString();
		Object result = createNativeQuery(sql)
				.setParameter("code", code)
				.getSingleResult();
		Locations location = null;
		if(result != null) {
			Object[] arrObj = (Object[]) result;
			location = new Locations();
			location.setId(arrObj[0].toString());
			
			Companies company = new Companies();
			company.setId(arrObj[1].toString());
			location.setCompany(company);
			
			location.setCode(arrObj[2].toString());
			location.setLocationName(arrObj[3].toString());
			location.setVersion(Long.valueOf(arrObj[4].toString()));
			location.setCreatedBy(arrObj[5].toString());
			location.setCreatedDate(((Timestamp)arrObj[6]).toLocalDateTime());
			
			if(arrObj[7] != null) {
				location.setUpdatedBy(arrObj[7].toString());
			}
			if(arrObj[8] != null) {
				location.setUpdatedDate(((Timestamp)arrObj[8]).toLocalDateTime());
			}
			location.setIsActive((Boolean)arrObj[9]);
			
		
		} 
		return location;
	}

	@Override
	public List<Locations> findAllFilterBySearch(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, id_company, code, locations_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM locations ");
		queryBuilder.append("WHERE code LIKE '%" + input + "%'OR locations_name LIKE '%" + input + "%' ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.getResultList();
		List<Locations> resultList = new ArrayList<Locations>();
		result.forEach(rs ->{
			Object[] arrObj = (Object[]) rs;
			Locations location  = new Locations();
			location.setId(arrObj[0].toString());
			
			Companies company = new Companies();
			company.setId(arrObj[1].toString());
			location.setCompany(company);
			
			location.setCode(arrObj[2].toString());
			location.setLocationName(arrObj[3].toString());
			location.setVersion(Long.valueOf(arrObj[4].toString()));
			location.setCreatedBy(arrObj[5].toString());
			location.setCreatedDate(((Timestamp)arrObj[6]).toLocalDateTime());
			
			if(arrObj[7] != null) {
				location.setUpdatedBy(arrObj[7].toString());
			}
			if(arrObj[8] != null) {
				location.setUpdatedDate(((Timestamp)arrObj[8]).toLocalDateTime());
			}
			location.setIsActive((Boolean)arrObj[9]);
			
			resultList.add(location);
		});
		return resultList;
	}
	
	
}
