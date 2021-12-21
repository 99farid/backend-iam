package com.lawencon.assetsmanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.base.ConnHandler;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RolePermissionsDaoTest {
	@Autowired
	RolesDao rolesDao;
	
	@Autowired
	private PermissionsDao permissionDao;
	
	@Autowired
	private RolePermissionsDao rolePermissionsDao;

	private String idInserted = "";

	@Test
	@Order(1)
	public void shouldSuccessInsert() throws Exception {
		ConnHandler.begin();
		Roles role = new Roles();
		role.setCode("adm");
		role.setCreatedBy("1");
		role.setIsActive(true);
		role.setRoleName("ADMIN");
		role = rolesDao.saveOrUpdate(role);
		
		Permissions permission = new Permissions();
		permission.setCode("CTR");
		permission.setCreatedBy("1");
		permission.setIsActive(true);
		permission.setPermissionName("Create Table Role");
		permission = permissionDao.saveOrUpdate(permission);
		
		RolePermissions rolePermission = new RolePermissions();
		rolePermission.setCreatedBy("1");
		rolePermission.setIsActive(true);
		rolePermission.setPermission(permission);
		rolePermission.setRole(role);
		
		
		RolePermissions result = rolePermissionsDao.saveOrUpdate(rolePermission);
		idInserted = result.getId();
		ConnHandler.commit();
		assertNotNull(result.getId());

	}

	@Test
	@Order(2)
	public void shouldSuccessGetById() throws Exception {
		
		RolePermissions result = rolePermissionsDao.findById(idInserted);
		
		assertNotNull(result);
	}

	@Test
	@Order(3)
	public void shouldSuccesUpdate() throws Exception{
		Permissions permission = new Permissions();
		permission.setCode("UTR");
		permission.setCreatedBy("1");
		permission.setIsActive(true);
		permission.setPermissionName("Update Table Role");
		permission = permissionDao.saveOrUpdate(permission);
		
		RolePermissions rolePermission = rolePermissionsDao.findById(idInserted);
		rolePermission.setPermission(permission);
		rolePermission.setUpdatedBy("1");
		ConnHandler.begin();
		RolePermissions result = rolePermissionsDao.saveOrUpdate(rolePermission);
		ConnHandler.commit();
		assertEquals(result.getVersion(), 1);
		assertEquals(result.getPermission().getPermissionName(), permission.getPermissionName());
		
	}
	
	@Test
	@Order(4)
	public void shouldSuccessFindAll() throws Exception{
		List<RolePermissions> result = rolePermissionsDao.findAll();
		
		assertEquals(result.size(), 1);
	}
	@Test
	@Order(5)
	public void shouldSuccesDelete() throws Exception{
		ConnHandler.begin();
		Boolean result = rolePermissionsDao.removeById(idInserted);
		ConnHandler.commit();
		assertEquals(result, true);
	}


}
