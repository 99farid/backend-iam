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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("permissions")
public class PermissionsController extends BaseIamController{

	@Autowired
	private PermissionsService permissionsService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResPemissionsDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResPemissionsDto result = permissionsService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResPermissionsDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResPermissionsDto result = permissionsService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("name")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResFilterByNameDto.class)))
	public ResponseEntity<?> findAllFilterByName(@RequestParam("q") String input) throws Exception {
		FindAllResFilterByNameDto result = permissionsService.findAllFilterByName(input);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert(@RequestBody Permissions data) throws Exception {
		InsertResDto permissions = permissionsService.insert(data);
		
		return new ResponseEntity<>(permissions, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update(@RequestBody Permissions data) throws Exception {
		UpdateResDto permissions = permissionsService.update(data);
		
		return new ResponseEntity<>(permissions, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = permissionsService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
