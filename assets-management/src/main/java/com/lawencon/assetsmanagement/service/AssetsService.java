package com.lawencon.assetsmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.assetsmanagement.dto.DeleteResDataDto;
import com.lawencon.assetsmanagement.dto.InsertResDto;
import com.lawencon.assetsmanagement.dto.UpdateResDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetByStatusResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.CountAssetResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterBySearchResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllFilterByTypeResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllForPdfAssetsExpiredDto;
import com.lawencon.assetsmanagement.dto.assets.FindAllResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.FindByIdResAssetsDto;
import com.lawencon.assetsmanagement.dto.assets.InsertReqDataAssetsDto;
import com.lawencon.assetsmanagement.model.Assets;

public interface AssetsService {

	FindAllResAssetsDto findAll() throws Exception;

	FindByIdResAssetsDto findById(String id) throws Exception;

	InsertResDto insert(InsertReqDataAssetsDto data, MultipartFile display, MultipartFile invoicePict) throws Exception;

	UpdateResDto update(Assets data) throws Exception;

	DeleteResDataDto removeById(String id) throws Exception;

	CountAssetResAssetsDto countAsset() throws Exception;

	CountAssetByStatusResAssetsDto countAssetByStatus(String statusCode) throws Exception;

	FindAllFilterByTypeResAssetsDto findAllFilterByType(String typeCode) throws Exception;

	FindAllFilterBySearchResAssetsDto findAllFilterBySearch(String input) throws Exception;
	
	FindAllForPdfAssetsExpiredDto findAllForPdf() throws Exception;

}
