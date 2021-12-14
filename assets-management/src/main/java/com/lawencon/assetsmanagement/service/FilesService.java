package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.model.Files;

public interface FilesService {

	Files findById(String id) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
}
