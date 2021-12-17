package com.lawencon.assetsmanagement.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindByIdResTrackActivityDto;
import com.lawencon.assetsmanagement.service.TrackActivityService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("track-activities")
public class TrackActivityController {

	@Autowired
	private TrackActivityService trackActivityService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResTrackActivityDto result = trackActivityService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id")String id) throws Exception {
		FindByIdResTrackActivityDto result = trackActivityService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
        HashMap<String, Object> map = new HashMap<>();
        
        FindAllResTrackActivityDto result = trackActivityService.findAll();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "track-activity", null);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=track-activity.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
