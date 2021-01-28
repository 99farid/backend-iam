package com.lawencon.elearning.service;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.elearning.model.Mahasiswa;

@Service
public class MahasiswaService extends BaseDaoImpl<Mahasiswa> {

	public void save(Mahasiswa data) throws Exception {
		save(data, null, null, true, true);
	}

	public Mahasiswa findById(String id) throws Exception {
		return getById(id);
	}

}
