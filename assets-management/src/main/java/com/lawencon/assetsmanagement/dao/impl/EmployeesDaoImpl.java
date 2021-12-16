package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class EmployeesDaoImpl extends BaseDaoImpl<Employees> implements EmployeesDao {

	@Override
	public List<Employees> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public Employees findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public Employees findByNip(String nip) throws Exception {
		Employees employee = null;
		try {
			
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT e.id AS id_employee, c.id, c.code, c.company_name, e.nip, e.full_name, e.phone_no, e.department, ");
			queryBuilder.append("e.ver, e.created_by, e.created_date, e.updated_by, e.updated_date, e.is_active  ");
			queryBuilder.append("FROM employees AS e ");
			queryBuilder.append("INNER JOIN companies AS c ON c.id = e.id_company ");
			queryBuilder.append("WHERE e.nip = :nip ");
			
			String sql = queryBuilder.toString();
			Object result = createNativeQuery(sql)
					.setParameter("nip", nip)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				employee = new Employees();
				employee.setId(objArr[0].toString());
				
				
				Companies companies = new Companies();
				companies.setId(objArr[1].toString());
				companies.setCode(objArr[2].toString());
				companies.setCompanyName(objArr[3].toString());
				
				employee.setCompany(companies);
				employee.setNip(objArr[4].toString());
				employee.setFullName(objArr[5].toString());
				employee.setPhoneNo(objArr[6].toString());
				employee.setDepartment(objArr[7].toString());
				employee.setVersion(Long.valueOf(objArr[8].toString()));
				employee.setCreatedBy(objArr[9].toString());
				employee.setCreatedDate(Timestamp.valueOf(objArr[10].toString()).toLocalDateTime());
				if (objArr[11] != null) {
					employee.setUpdatedBy(objArr[11].toString());
				}
				if (objArr[12] != null) {
					employee.setUpdatedDate(Timestamp.valueOf(objArr[12].toString()).toLocalDateTime());
				}
				employee.setIsActive(Boolean.valueOf(objArr[13].toString()));
			}
		} catch (NoResultException e) {
			throw new NoResultException("Nip Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Nip Found More Than One");
		}
		
		return employee;
	}
	
	@Override
	public Employees saveOrUpdate(Employees data) throws Exception {
		return save(data);
	}
	
	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}