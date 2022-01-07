package com.lawencon.assetsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.constant.PermissionDeleteCode;
import com.lawencon.assetsmanagement.constant.ResponseMsg;
import com.lawencon.assetsmanagement.dao.CompaniesDao;
import com.lawencon.assetsmanagement.dao.EmployeesDao;
import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDataDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.employees.FindAllResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByIdResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByResNipDto;
import com.lawencon.assetsmanagement.exception.ValidationIamException;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.model.RolePermissions;
import com.lawencon.assetsmanagement.service.EmployeesService;
import com.lawencon.util.ExcelUtil;

@Service
public class EmployeesServiceImpl extends BaseIamServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private CompaniesDao companiesDao;
	
	@Autowired
	private ExcelUtil excelUtil;
	
	public FindAllResEmployeesDto findAll() throws Exception {
		FindAllResEmployeesDto result = new FindAllResEmployeesDto();
		result.setData(employeesDao.findAll());
		result.setMsg(null);
		
		return result;
	}
	
	public FindByIdResEmployeesDto findById(String id) throws Exception {
		FindByIdResEmployeesDto result = new FindByIdResEmployeesDto();
		result.setData(employeesDao.findById(id));
		result.setMsg(null);
		
		return result;
	}
	

	@Override
	public FindByResNipDto findByNip(String nip) throws Exception {
		FindByResNipDto result = new FindByResNipDto();
		result.setData(employeesDao.findByNip(nip));
		result.setMsg(null);
		
		return result;
	}	
	private void validationInsert(Employees data) throws Exception{
		if(data != null) {
			if(data.getNip() != null) {
				Employees employee = employeesDao.findByNip(data.getNip());
				if(employee !=null ) {
					throw new ValidationIamException("NIP already Used");
				}
			}else {
				throw new ValidationIamException("Data is not complete");
			}
			if(data.getFullName() == null || data.getCompany() == null || data.getDepartment() == null || data.getEmail() == null) {
				throw new ValidationIamException("Data is not complate");
			}
		}else {
			throw new ValidationIamException("Data is not found");
		}
	}
	@Override
	public InsertResDto insert(Employees data) throws Exception {
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			validationInsert(data);
			data.setCreatedBy(getIdAuth());
			begin();
			Employees employee = employeesDao.saveOrUpdate(data);
			commit();
			
			insertResDataDto.setId(employee.getId());
			insertResDto.setData(insertResDataDto);
			insertResDto.setMsg(ResponseMsg.SUCCESS_INSERT.getMsg());
			
			return insertResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public InsertResDto insertExcel(MultipartFile data) throws Exception{
		
		try {
			InsertResDto insertResDto = new InsertResDto();
			InsertResDataDto insertResDataDto = new InsertResDataDto();
			excelUtil.init("data", data.getInputStream());
			for(int i = 1; i<excelUtil.getRowCountInSheet(); i++) {
				Employees employee = new Employees();
				Companies company = companiesDao.findByCode(excelUtil.getCellData(i, 0));
				employee.setCompany(company);
				employee.setNip(excelUtil.getCellData(i, 1));
				employee.setFullName(excelUtil.getCellData(i, 2));
				employee.setPhoneNo(excelUtil.getCellData(i, 3));
				employee.setEmail(excelUtil.getCellData(i, 4));
				employee.setCreatedBy(getIdAuth());
				employee.setIsActive(true);
				employee.setDepartment(excelUtil.getCellData(i, 5));
				begin();
				employee = employeesDao.saveOrUpdate(employee);
				commit();
				insertResDataDto.setId(employee.getId());
			}
			insertResDto.setData(insertResDataDto);
			
			return insertResDto;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}
	private void validationUpdate(Employees data) throws Exception{
		if(data != null) {
			if(data.getId() != null) {
				Employees employee = employeesDao.findById(data.getId());
				if(employee == null) {
					throw new ValidationIamException("Data not found");
				}
			}else {
				throw new ValidationIamException("Data not found");
			}
			if(data.getFullName() == null || data.getCompany() == null || data.getDepartment() == null || data.getEmail() == null) {
				throw new ValidationIamException("Data not complete");
			}
				
		}else {
			throw new ValidationIamException("Data not found");
		}
	}
	@Override
	public UpdateResDto update(Employees data) throws Exception {
		try {
			UpdateResDto updateResDto = new UpdateResDto();
			UpdateResDataDto updateResDataDto = new UpdateResDataDto();
			validationUpdate(data);
			data.setUpdatedBy(getIdAuth());
			begin();
			Employees employeesUpdate = employeesDao.saveOrUpdate(data);
			commit();
			
			updateResDataDto.setVersion(employeesUpdate.getVersion());
			updateResDto.setData(updateResDataDto);
			updateResDto.setMsg(ResponseMsg.SUCCESS_UPDATE.getMsg());
			
			return updateResDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public DeleteResDataDto removeById(String id) throws Exception {
		try {
			DeleteResDataDto deleteResDataDto = new DeleteResDataDto();
			List<RolePermissions> listPermission = getUserPermission();
			boolean isForbidden = true;
			for(RolePermissions i : listPermission) {
				if(i.getPermission().getCode().equals(PermissionDeleteCode.DELETE_EMPLOYEE.getCode())) {
					isForbidden = false;
				}
			}
			if(isForbidden) {
				throw new ValidationIamException("Invalid Permission");
			}
			begin();
			boolean resultDelete = employeesDao.removeById(id);
			commit();
			
			if (resultDelete) {
				deleteResDataDto.setMsg(ResponseMsg.SUCCESS_DELETE.getMsg());
			} else {
				deleteResDataDto.setMsg(ResponseMsg.FAILED_DELETE.getMsg());
			}
			
			return deleteResDataDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Override
	public byte[] downloadTemplate() throws Exception {
		List<Companies> listCompanies = companiesDao.findAll();
		String[][] companiesData = new String [listCompanies.size()+1][2];
		companiesData[0][0] = "Company";
		companiesData[0][1] = "Code";		
		for(int i = 1 ; i< companiesData.length; i++) {
			companiesData[i][0] = listCompanies.get(i-1).getCompanyName();
			companiesData[i][1] = listCompanies.get(i-1).getCode();
		}
		String [][] rowName = {{"Kode Perusahaan", "NIP", "Nama Lengkap", "Nomor Telpon", "Email", "Department"}};
		byte[] result = excelUtil
							.init()
							.setSheetAndData("data", rowName)
							.setSheetAndData("Company Kode", companiesData)
							.getByteArrayFile();
		return result;
	}
}