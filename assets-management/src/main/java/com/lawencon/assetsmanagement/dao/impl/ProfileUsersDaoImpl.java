package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.ProfileUsersDao;
import com.lawencon.assetsmanagement.model.ProfileUsers;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class ProfileUsersDaoImpl extends BaseDaoImpl<ProfileUsers> implements ProfileUsersDao {

	@Override
	public List<ProfileUsers> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public ProfileUsers findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public ProfileUsers findByUser(String userId) throws Exception {
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT pu FROM ProfileUsers pu ");
			queryBuilder.append("INNER JOIN FETCH pu.user ");
			queryBuilder.append("INNER JOIN FETCH pu.employee e ");
			queryBuilder.append("INNER JOIN FETCH e.company ");
			queryBuilder.append("INNER JOIN FETCH pu.profilePict ");
			queryBuilder.append("WHERE pu.user.id = :userId ");

			String sql = queryBuilder.toString();
			
			return createQuery(sql, ProfileUsers.class)
					.setParameter("userId", userId)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Profile User Found More Than One");
		}	
	}
	
	@Override
	public ProfileUsers saveOrUpdate(ProfileUsers data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}