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
		queryBuilder.append("SELECT rp.id, id_role, r.code, id_permission, p.permission_name, ");
		queryBuilder.append("rp.ver, rp.created_by, rp.created_date, rp.updated_by, rp.updated_date, rp.is_active ");
		queryBuilder.append("FROM role_permissions rp ");
		queryBuilder.append("INNER JOIN permissions p ON p.id =  rp.id_permission ");
		queryBuilder.append("INNER JOIN roles r ON r.id = rp.id_role ");
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
			role.setCode(arrObj[2].toString());
			rolePermissions.setRole(role);
			
			Permissions permissions = new Permissions();
			permissions.setId(arrObj[3].toString());
			permissions.setPermissionName(arrObj[4].toString());
			rolePermissions.setPermission(permissions);
			
			rolePermissions.setVersion(Long.valueOf(arrObj[5].toString()));
			rolePermissions.setCreatedBy(arrObj[6].toString());
			rolePermissions.setCreatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			
			if(arrObj[8] != null) {
				rolePermissions.setUpdatedBy(arrObj[8].toString());
			}
			if(arrObj[9] != null) {
				rolePermissions.setUpdatedDate(((Timestamp)arrObj[9]).toLocalDateTime());
			}
			rolePermissions.setIsActive((Boolean)arrObj[10]);
			
			resultList.add(rolePermissions);
		});
		return resultList;
	}

	@Override
	public List<RolePermissions> findAllFilterByRoleCode(String roleCode) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT rp.id, id_role, r.code, id_permission, p.permission_link, ");
		queryBuilder.append("rp.ver, rp.created_by, rp.created_date, rp.updated_by, rp.updated_date, rp.is_active ");
		queryBuilder.append("FROM role_permissions rp ");
		queryBuilder.append("INNER JOIN permissions p ON p.id =  rp.id_permission ");
		queryBuilder.append("INNER JOIN roles r ON r.id = rp.id_role ");
		queryBuilder.append("WHERE r.code = :roleCode ");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql)
				.setParameter("roleCode", roleCode)
				.getResultList();
		List<RolePermissions> resultList = new ArrayList<RolePermissions>();
		result.forEach(rs->{
			Object[] arrObj = (Object[]) rs;
			RolePermissions rolePermissions = new RolePermissions();
			rolePermissions.setId(arrObj[0].toString());
			
			Roles role = new Roles();
			role.setId(arrObj[1].toString());
			role.setCode(arrObj[2].toString());
			rolePermissions.setRole(role);
			
			Permissions permissions = new Permissions();
			permissions.setId(arrObj[3].toString());
			permissions.setPermissionLink(arrObj[4].toString());
			rolePermissions.setPermission(permissions);
			
			rolePermissions.setVersion(Long.valueOf(arrObj[5].toString()));
			rolePermissions.setCreatedBy(arrObj[6].toString());
			rolePermissions.setCreatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			
			if(arrObj[8] != null) {
				rolePermissions.setUpdatedBy(arrObj[8].toString());
			}
			if(arrObj[9] != null) {
				rolePermissions.setUpdatedDate(((Timestamp)arrObj[9]).toLocalDateTime());
			}
			rolePermissions.setIsActive((Boolean)arrObj[10]);
			
			resultList.add(rolePermissions);
		});
		return resultList;
	}

}