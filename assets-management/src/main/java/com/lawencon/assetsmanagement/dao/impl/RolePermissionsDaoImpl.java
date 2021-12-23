package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.RolePermissionsDao;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class RolePermissionsDaoImpl extends BaseDaoImpl<RolePermissions> implements RolePermissionsDao {
	
	@Override
	public List<RolePermissions> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public RolePermissions findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public RolePermissions saveOrUpdate(RolePermissions data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<RolePermissions> findAllFilterByRole(String idRole) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, id_role, id_permission, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM role_permissions ");
		queryBuilder.append("WHERE id_role = :idRole ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.setParameter("idRole", idRole)
				.getResultList();
		List<RolePermissions> resultList = new ArrayList<RolePermissions>();
		result.forEach(rs->{
			Object[] arrObj = (Object[]) rs;
			RolePermissions rolePermissions = new RolePermissions();
			rolePermissions.setId(arrObj[0].toString());
			
			Roles role = new Roles();
			role.setId(arrObj[1].toString());
			rolePermissions.setRole(role);
			
			Permissions permissions = new Permissions();
			permissions.setId(arrObj[2].toString());
			rolePermissions.setPermission(permissions);
			
			rolePermissions.setVersion(Long.valueOf(arrObj[3].toString()));
			rolePermissions.setCreatedBy(arrObj[4].toString());
			rolePermissions.setCreatedDate(((Timestamp)arrObj[5]).toLocalDateTime());
			
			if(arrObj[6] != null) {
				rolePermissions.setUpdatedBy(arrObj[6].toString());
			}
			if(arrObj[7] != null) {
				rolePermissions.setUpdatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			}
			rolePermissions.setIsActive((Boolean)arrObj[8]);
			
			resultList.add(rolePermissions);
		});
		return resultList;
	}

}