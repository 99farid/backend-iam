package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.elearning.model.Mahasiswa;
import com.lawencon.helper.Callback;

public interface MahasiswaDao {

	void insert(Mahasiswa data, Callback before) throws Exception;

	void update(Mahasiswa data, Callback before) throws Exception;

	Optional<Mahasiswa> findById(String id) throws Exception;
	
	List<Mahasiswa> findAll() throws Exception;

}
