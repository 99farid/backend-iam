package com.lawencon.assetsmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.assetsmanagement.exception.ValidationIamException;

public class BaseIamController {
	
	protected <T> T convertToModel (String src, Class<T> clazz) throws Exception{
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		return new ObjectMapper()
				.registerModule(javaTimeModule)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.readValue(src, clazz);
	}
	
	@ExceptionHandler(ValidationIamException.class)
	ResponseEntity<?> validationExceptionCatch(ValidationIamException e){
		Map<String, Object> errMap = new HashMap<>();
		e.printStackTrace();
		errMap.put("msg", e.getMessage());
		
		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoResultException.class)
	ResponseEntity<?> noResultExceptionCatch(NoResultException e){
		Map<String, Object> errMap = new HashMap<>();
		e.printStackTrace();
		errMap.put("msg", e.getMessage());
		
		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NonUniqueResultException.class)
	ResponseEntity<?> nonUniqueResultExceptionCatch(NonUniqueResultException e){
		Map<String, Object> errMap = new HashMap<>();
		errMap.put("msg", e.getMessage());
		
		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<?> nonConstraintViolationExceptionCatch(ConstraintViolationException e){
		Map<String, Object> errMap = new HashMap<>();
		errMap.put("msg", e.getMessage());
		
		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NullPointerException.class)
	ResponseEntity<?> nullPointerExceptionCatch(NullPointerException e){
		Map<String, Object> errMap = new HashMap<>();
		errMap.put("msg", e.getMessage());
		
		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}
}
