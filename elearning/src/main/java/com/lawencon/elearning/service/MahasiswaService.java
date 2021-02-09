package com.lawencon.elearning.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.elearning.model.Mahasiswa;

@Service
@Transactional
public class MahasiswaService extends BaseDaoImpl<Mahasiswa> {

	public void insert(Mahasiswa data) throws Exception {
		save(data, null, null, true, true);
	}

	public void update(Mahasiswa data) throws Exception {
		Mahasiswa mhsDb = findById(data.getId());
		data.setCreatedAt(mhsDb.getCreatedAt());
		data.setCreatedBy(mhsDb.getCreatedBy());
		data.setUpdatedBy(mhsDb.getCreatedBy());

		save(data, null, null, true, true);
	}

	public Mahasiswa findById(String id) throws Exception {
		return getById(id);
	}

}
