package com.lawencon.assetsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.files.FindByIdResFilesDto;
import com.lawencon.assetsmanagement.service.FilesService;

public class FilesController extends BaseIamController {
	@Autowired
	private FilesService filesService;
	
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResFilesDto result = filesService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   filesService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
