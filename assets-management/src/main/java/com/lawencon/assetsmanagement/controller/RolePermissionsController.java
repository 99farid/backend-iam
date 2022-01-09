package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResFilterByRoleCodeDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResFilterByRoleDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindAllResRolePermissionsDto;
import com.lawencon.assetsmanagement.dto.rolepermissions.FindByIdResRolePermissionsDto;
import com.lawencon.assetsmanagement.service.RolePermissionsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("role-permissions")
public class RolePermissionsController extends BaseIamController{

	@Autowired
	private RolePermissionsService rolePermissionsService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResRolePermissionsDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResRolePermissionsDto result = rolePermissionsService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResRolePermissionsDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResRolePermissionsDto result = rolePermissionsService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("role")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResFilterByRoleDto.class)))
	public ResponseEntity<?> findAllFilterByRole(@RequestParam("q") String idRole) throws Exception {
		FindAllResFilterByRoleDto result = rolePermissionsService.findAllFilterByRole(idRole);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("role-code")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResFilterByRoleCodeDto.class)))
	public ResponseEntity<?> findAllFilterByRoleCode(@RequestParam("q") String roleCode) throws Exception {
		FindAllResFilterByRoleCodeDto result = rolePermissionsService.findAllFilterByRoleCode(roleCode);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		DeleteResDataDto result = rolePermissionsService.removeById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
