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
import com.lawencon.assetsmanagement.dto.locations.FindAllResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindByIdResLocationsDto;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.service.LocationsService;
@RestController
@RequestMapping("locations")
public class LocationsController {
	@Autowired
	private LocationsService locationsService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResLocationsDto result = locationsService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResLocationsDto result = locationsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert (@RequestBody Locations data) throws Exception{
		InsertResDto result = locationsService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update (@RequestBody Locations data) throws Exception{
		UpdateResDto result = locationsService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   locationsService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
