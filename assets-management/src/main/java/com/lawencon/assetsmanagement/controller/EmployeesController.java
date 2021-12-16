package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.employees.FindAllResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByIdResEmployeesDto;
import com.lawencon.assetsmanagement.dto.employees.FindByResNipDto;
import com.lawencon.assetsmanagement.model.Employees;
import com.lawencon.assetsmanagement.service.EmployeesService;

@RestController
@RequestMapping("employees")
public class EmployeesController {

	@Autowired
	private EmployeesService employeesService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResEmployeesDto result = employeesService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResEmployeesDto result = employeesService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("nip")
	public ResponseEntity<?> findByNip(@RequestParam("nip") String nip) throws Exception {
		FindByResNipDto result = employeesService.findByNip(nip);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Employees data) throws Exception {
		InsertResDto employees = employeesService.insert(data);
		
		return new ResponseEntity<>(employees, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Employees data) throws Exception {
		UpdateResDto employees = employeesService.update(data);
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = employeesService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}