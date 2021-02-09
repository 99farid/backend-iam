package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.elearning.model.Mahasiswa;
import com.lawencon.elearning.service.MahasiswaService;

@RestController
@RequestMapping("test")
public class IndexController {

	@Autowired
	private MahasiswaService mahasiswaService;

	@GetMapping("{id}")
	public ResponseEntity<?> getMhs(@PathVariable("id") String id) {
		try {
			Mahasiswa mhs = mahasiswaService.findById(id);
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody String data) {
		try {
			Mahasiswa mhsTemp = new ObjectMapper().readValue(data, Mahasiswa.class);
			mahasiswaService.insert(mhsTemp);
			return new ResponseEntity<>(mhsTemp.getId(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody String data) {
		try {
			Mahasiswa mhsTemp = new ObjectMapper().readValue(data, Mahasiswa.class);
			mahasiswaService.update(mhsTemp);
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
