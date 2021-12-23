package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.RolesDao;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao {

	@Override
	public List<Roles> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Roles findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Roles saveOrUpdate(Roles data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public Roles findByCode(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, role_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM roles ");
		queryBuilder.append("WHERE code = :code");
		
		String sql = queryBuilder.toString();
		Object result = createNativeQuery(sql)
				.setParameter("code", code)
				.getSingleResult();
		Roles role = null;
		
		if(result != null) {
			Object[] arrObj = (Object[]) result;
			role = new Roles();
			role.setId(arrObj[0].toString());
			role.setCode(arrObj[1].toString());
			role.setRoleName(arrObj[2].toString());
			role.setVersion(Long.valueOf(arrObj[3].toString()));
			role.setCreatedBy(arrObj[4].toString());
			role.setCreatedDate(((Timestamp)arrObj[5]).toLocalDateTime());
			if(arrObj[6] != null) {
				role.setUpdatedBy(arrObj[6].toString());
			}
			if(arrObj[7] != null) {
				role.setUpdatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			}
			role.setIsActive((Boolean) arrObj[8]);
			
		} 
		return role;
	}

}
