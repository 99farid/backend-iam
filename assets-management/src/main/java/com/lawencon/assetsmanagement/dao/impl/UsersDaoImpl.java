package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.UsersDao;
import com.lawencon.assetsmanagement.model.Roles;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

	@Override
	public List<Users> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Users findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Users findByEmail(String email) throws Exception {
		Users user = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT u.id AS id_users, r.id, u.id_role, u.email, u.pass, r.code, r.role_name, ");
			queryBuilder.append("u.ver, u.created_by, u.created_date, u.updated_by, u.updated_date, u.is_active ");
			queryBuilder.append("FROM users AS u ");
			queryBuilder.append("INNER JOIN roles AS r ON r.id = u.id_role ");
			queryBuilder.append("WHERE u.email = :email ");
			
			String sql = queryBuilder.toString();
			Object result = createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				user = new Users();
				user.setId(objArr[0].toString());
				
				Roles roles = new Roles();
				roles.setId(objArr[1].toString());
				roles.setCode(objArr[2].toString());
				roles.setRoleName(objArr[3].toString());
				
				user.setRole(roles);
				user.setEmail(objArr[4].toString());
				user.setPass(objArr[5].toString());
				user.setVersion(Long.valueOf(objArr[6].toString()));
				user.setCreatedBy(objArr[7].toString());
				user.setCreatedDate(Timestamp.valueOf(objArr[8].toString()).toLocalDateTime());
				if (objArr[9] != null) {
					user.setUpdatedBy(objArr[9].toString());
				}
				if (objArr[10] != null) {
					user.setUpdatedDate(Timestamp.valueOf(objArr[10].toString()).toLocalDateTime());
				}
				user.setIsActive(Boolean.valueOf(objArr[11].toString()));
			}
		} catch (NoResultException e) {
			throw new NoResultException("Email Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Email Found More Than One");
		}
		
		return user;
	}
	
	@Override
	public Users saveOrUpdate(Users data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	
	
}