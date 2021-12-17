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
import com.lawencon.assetsmanagement.dto.permissions.FindAllResFilterByNameDto;
import com.lawencon.assetsmanagement.dto.permissions.FindAllResPemissionsDto;
import com.lawencon.assetsmanagement.dto.permissions.FindByIdResPermissionsDto;
import com.lawencon.assetsmanagement.model.Permissions;
import com.lawencon.assetsmanagement.service.PermissionsService;

@RestController
@RequestMapping("permissions")
public class PermissionsController {

	@Autowired
	private PermissionsService permissionsService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResPemissionsDto result = permissionsService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResPermissionsDto result = permissionsService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("name")
	public ResponseEntity<?> findAllFilterByName(@RequestParam("q") String input) throws Exception {
		FindAllResFilterByNameDto result = permissionsService.findAllFilterByName(input);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Permissions data) throws Exception {
		InsertResDto permissions = permissionsService.insert(data);
		
		return new ResponseEntity<>(permissions, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Permissions data) throws Exception {
		UpdateResDto permissions = permissionsService.update(data);
		
		return new ResponseEntity<>(permissions, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = permissionsService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
