package com.lawencon.assetsmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BaseIamController {
	
	protected <T> T convertToModel (String src, Class<T> clazz) throws Exception{
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		return new ObjectMapper()
				.registerModule(javaTimeModule)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.readValue(src, clazz);
	}
}
