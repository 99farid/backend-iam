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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.roles.FindAllResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.FindByIdResRolesDto;
import com.lawencon.assetsmanagement.dto.roles.InsertReqDataRolesDto;
import com.lawencon.assetsmanagement.dto.roles.UpdateReqRolesDto;
import com.lawencon.assetsmanagement.service.RolesService;

@RestController
@RequestMapping("roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResRolesDto result = rolesService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResRolesDto result = rolesService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertReqDataRolesDto data) throws Exception {
		InsertResDto roles = rolesService.insert(data);
		
		return new ResponseEntity<>(roles, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UpdateReqRolesDto data) throws Exception {
		UpdateResDto roles = rolesService.update(data);
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = rolesService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
