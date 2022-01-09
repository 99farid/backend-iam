package com.lawencon.assetsmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindAllResTrackActivityDto;
import com.lawencon.assetsmanagement.dto.trackactivity.FindByIdResTrackActivityDto;
import com.lawencon.assetsmanagement.service.TrackActivityService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("track-activities")
public class TrackActivityController extends BaseIamController {

	@Autowired
	private TrackActivityService trackActivityService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindAllResTrackActivityDto.class)))
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResTrackActivityDto result = trackActivityService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data", content = @Content(schema = @Schema (implementation = FindByIdResTrackActivityDto.class)))
	public ResponseEntity<?> findById(@PathVariable("id")String id) throws Exception {
		FindByIdResTrackActivityDto result = trackActivityService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
	@ApiResponse(responseCode = "200", description = "Successfuly Get Data")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
    	Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
        FindAllResTrackActivityDto result = trackActivityService.findAll();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "track-activity", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=track-activity.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
    
    @GetMapping("/send-pdf")
    @ApiResponse(responseCode = "200", description = "Successfuly Send Data")
	public ResponseEntity<?> sendFileToEmail() throws Exception {
		SendResEmailDto result = trackActivityService.sendFileToEmail();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
