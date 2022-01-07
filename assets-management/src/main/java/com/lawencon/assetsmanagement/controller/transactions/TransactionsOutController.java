package com.lawencon.assetsmanagement.controller.transactions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.SendResEmailDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllForPdfTrxOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdEmployeeDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdGeneralItemDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResFilterByIdLocationDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindAllResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.FindByIdResTransactionsOutDto;
import com.lawencon.assetsmanagement.dto.transactionsout.InsertReqDataTransactionsOutDto;
import com.lawencon.assetsmanagement.service.TransactionsOutService;
import com.lawencon.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("transactions-out")
public class TransactionsOutController {

	@Autowired
	private TransactionsOutService transactionsOutService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllResTransactionsOutDto result = transactionsOutService.findAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		FindByIdResTransactionsOutDto result = transactionsOutService.findById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("employee")
	public ResponseEntity<?> findAllFilterByIdEmployee() throws Exception {
		FindAllResFilterByIdEmployeeDto result = transactionsOutService.findAllFilterByIdEmployee();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("location")
	public ResponseEntity<?> findAllFilterByIdLocation() throws Exception {
		FindAllResFilterByIdLocationDto result = transactionsOutService.findAllFilterByIdLocation();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("general-item")
	public ResponseEntity<?> findAllFilterByIdGeneralItem() throws Exception {
		FindAllResFilterByIdGeneralItemDto result = transactionsOutService.findAllFilterByIdGeneralItem();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertReqDataTransactionsOutDto data) throws Exception {
		InsertResDto transOut = transactionsOutService.insert(data);
		
		return new ResponseEntity<>(transOut, HttpStatus.CREATED);
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
    	Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Lawencon Internasional");
        
        FindAllForPdfTrxOutDto result = transactionsOutService.findAllForPdf();
        byte[] data = JasperUtil.responseToByteArray(result.getData(), "transactions-out", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=transactions-out.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
    
    @GetMapping("/send-pdf")
	public ResponseEntity<?> sendFileToEmail() throws Exception {
		SendResEmailDto result = transactionsOutService.sendFileToEmail();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}