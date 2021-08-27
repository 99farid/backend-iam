package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.elearning.model.Mahasiswa;
import com.lawencon.helper.Callback;

@Repository
public class MahasiswaDaoImpl extends BaseDaoImpl<Mahasiswa> implements MahasiswaDao {

	@Override
	public void insert(Mahasiswa data, Callback before) throws Exception {
		save(data, before, null);
	}

	@Override
	public void update(Mahasiswa data, Callback before) throws Exception {
		save(data, before, null);
	}

	@Override
	public Optional<Mahasiswa> findById(String id) throws Exception {
		Mahasiswa mhs = getById(id);
		return Optional.ofNullable(mhs);
	}
	
	@Override
	public List<Mahasiswa> findAll() throws Exception {
		return getAll();
	}

}
