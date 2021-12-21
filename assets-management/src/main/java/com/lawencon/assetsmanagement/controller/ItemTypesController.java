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
import com.lawencon.assetsmanagement.dto.itemtypes.FindAllResItemTypesDto;
import com.lawencon.assetsmanagement.dto.itemtypes.FindByIdResItemTypesDto;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.assetsmanagement.service.ItemTypesService;

@RestController
@RequestMapping("item-types")
public class ItemTypesController extends BaseIamController{
	@Autowired
	private ItemTypesService typeService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResItemTypesDto result = typeService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResItemTypesDto result = typeService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestBody ItemTypes data) throws Exception{
		InsertResDto result = typeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update (@RequestBody ItemTypes data) throws Exception{
		UpdateResDto result = typeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   typeService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
