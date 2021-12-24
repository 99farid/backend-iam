package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.GeneralTemplateDao;
import com.lawencon.assetsmanagement.model.GeneralTemplate;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class GeneralTemplateDaoImpl extends BaseDaoImpl<GeneralTemplate> implements GeneralTemplateDao{

	@Override
	public List<GeneralTemplate> findAll() throws Exception {
		return getAll();
	}

	@Override
	public GeneralTemplate findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public GeneralTemplate saveOrUpdate(GeneralTemplate data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public GeneralTemplate findByCode(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, data_template, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM general_template ");
		queryBuilder.append("WHERE code = :code");
		
		String sql = queryBuilder.toString();
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		GeneralTemplate generalTemplate = null;
		if(result != null) {
			Object[] arrObj = (Object[]) result;
			generalTemplate = new GeneralTemplate();
			generalTemplate.setId(arrObj[0].toString());
			generalTemplate.setCode(arrObj[1].toString());
			generalTemplate.setDataTemplate(arrObj[2].toString());
			generalTemplate.setVersion(Long.valueOf(arrObj[3].toString()));
			generalTemplate.setCreatedBy(arrObj[4].toString());
			generalTemplate.setCreatedDate(((Timestamp)arrObj[5]).toLocalDateTime());
			if(arrObj[6] != null) {
				generalTemplate.setUpdatedBy(arrObj[6].toString());
			}
			if(arrObj[7] != null) {
				generalTemplate.setUpdatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			}
			generalTemplate.setIsActive((Boolean) arrObj[8]);
		}
		
				
				
		return generalTemplate;
	}

	
}
