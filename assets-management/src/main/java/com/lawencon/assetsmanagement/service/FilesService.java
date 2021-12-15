package com.lawencon.assetsmanagement.service;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.files.FindByIdResFilesDto;

public interface FilesService {

	FindByIdResFilesDto findById(String id) throws Exception;
	
	DeleteResDataDto removeById(String id) throws Exception;
	
}
