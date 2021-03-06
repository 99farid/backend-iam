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
import com.lawencon.assetsmanagement.dto.locations.FindAllFilterBySearchResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindAllResLocationsDto;
import com.lawencon.assetsmanagement.dto.locations.FindByIdResLocationsDto;
import com.lawencon.assetsmanagement.model.Locations;
import com.lawencon.assetsmanagement.service.LocationsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@RestController
@RequestMapping("locations")
public class LocationsController extends BaseIamController{
	@Autowired
	private LocationsService locationsService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResLocationsDto.class)))
	public ResponseEntity<?> findAll() throws Exception{
		FindAllResLocationsDto result = locationsService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResLocationsDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception{
		FindByIdResLocationsDto result = locationsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("search")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllFilterBySearchResLocationsDto.class)))
	public ResponseEntity<?> findAllFilterBySearch(@RequestParam("query") String input) throws Exception {
		FindAllFilterBySearchResLocationsDto result = locationsService.findAllFilterBySearch(input);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Successfuly Insert Data", content = @Content(schema = @Schema (implementation = InsertResDto.class)))
	public ResponseEntity<?> insert (@RequestBody Locations data) throws Exception{
		InsertResDto result = locationsService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Update Data", content = @Content(schema = @Schema (implementation = UpdateResDto.class)))
	public ResponseEntity<?> update (@RequestBody Locations data) throws Exception{
		UpdateResDto result = locationsService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping ("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Delete Data", content = @Content(schema = @Schema (implementation = DeleteResDataDto.class)))
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception{
		DeleteResDataDto result =   locationsService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
