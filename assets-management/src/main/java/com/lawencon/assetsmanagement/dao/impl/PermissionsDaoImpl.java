package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.PermissionsDao;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class PermissionsDaoImpl extends BaseDaoImpl<Permissions> implements PermissionsDao {
	
	@Override
	public List<Permissions> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Permissions findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public List<Permissions> findAllFilterByName(String input) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, permission_name, ");
		queryBuilder.append("ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM permissions AS p ");
		queryBuilder.append("WHERE permission_name LIKE :input%% OR permission_name LIKE %%:input ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.setParameter("input", input)
				.getResultList();
		
		List<Permissions> resultListPermissions = new ArrayList<>();
		result.forEach(rs -> {
			
			Object[] objArr = (Object[]) rs;
			
			Permissions permission = new Permissions();
			permission.setId(objArr[0].toString());
			permission.setCode(objArr[1].toString());
			permission.setPermissionName(objArr[2].toString());
			permission.setVersion(Long.valueOf(objArr[3].toString()));
			permission.setCreatedBy(objArr[4].toString());
			permission.setCreatedDate(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			if (objArr[6] != null) {
				permission.setUpdatedBy(objArr[6].toString());
			}
			if (objArr[7] != null) {
				permission.setUpdatedDate(Timestamp.valueOf(objArr[7].toString()).toLocalDateTime());
			}
			permission.setIsActive(Boolean.valueOf(objArr[8].toString()));
			
			resultListPermissions.add(permission);
		});
		
		return resultListPermissions;
	}
	
	@Override
	public Permissions saveOrUpdate(Permissions data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
