package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class CompaniesDaoImpl extends BaseDaoImpl<Companies> implements CompaniesDao {

	@Override
	public List<Companies> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Companies findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Companies saveOrUpdate(Companies data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<Companies> findAllFilterBySearch(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(
				"SELECT id, code, company_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM companies ");
		queryBuilder.append("WHERE code LIKE '%" + input + "%' OR company_name LIKE '%" + input + "%'");

		String sql = queryBuilder.toString();

		List<?> result = createNativeQuery(sql).getResultList();
		List<Companies> resultList = new ArrayList<Companies>();
			result.forEach(rs -> {
				Object[] arrObj = (Object[]) rs;
				Companies company = new Companies();
				company.setId(arrObj[0].toString());
				company.setCode(arrObj[1].toString());
				company.setCompanyName(arrObj[2].toString());
				company.setVersion(Long.valueOf(arrObj[3].toString()));
				company.setCreatedBy(arrObj[4].toString());
				company.setCreatedDate(((Timestamp) arrObj[5]).toLocalDateTime());
				if (arrObj[6] != null) {
					company.setUpdatedBy(arrObj[6].toString());
				}
				if (arrObj[7] != null) {
					company.setUpdatedDate(((Timestamp) arrObj[7]).toLocalDateTime());
				}
				company.setIsActive((Boolean) arrObj[8]);

				resultList.add(company);
			});

		
		return resultList;
	}

	@Override
	public Companies findByCode(String code) throws Exception {
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(
					"SELECT id, code, company_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
			queryBuilder.append("FROM companies ");
			queryBuilder.append("WHERE code = :code");

			String sql = queryBuilder.toString();
			Object result = createNativeQuery(sql)
					.setParameter("code", code)
					.getSingleResult();
			Companies company = null;

			if (result != null) {
				Object[] arrObj = (Object[]) result;
				company = new Companies();
				company.setId(arrObj[0].toString());
				company.setCode(arrObj[1].toString());
				company.setCompanyName(arrObj[2].toString());
				company.setVersion(Long.valueOf(arrObj[3].toString()));
				company.setCreatedBy(arrObj[4].toString());
				company.setCreatedDate(((Timestamp) arrObj[5]).toLocalDateTime());
				if (arrObj[6] != null) {
					company.setUpdatedBy(arrObj[6].toString());
				}
				if (arrObj[7] != null) {
					company.setUpdatedDate(((Timestamp) arrObj[7]).toLocalDateTime());
				}
				company.setIsActive((Boolean) arrObj[8]);

			}
			return company;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}