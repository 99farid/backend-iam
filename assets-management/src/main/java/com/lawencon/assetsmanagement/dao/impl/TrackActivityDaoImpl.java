package com.lawencon.assetsmanagement.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.TrackActivityDao;
import com.lawencon.assetsmanagement.model.TrackActivity;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class TrackActivityDaoImpl extends BaseDaoImpl<TrackActivity> implements TrackActivityDao {

	@Override
	public List<TrackActivity> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TrackActivity findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public TrackActivity saveOrUpdate(TrackActivity data) throws Exception {
		return save(data);
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT count(id) FROM track_activity";
		Object result = createNativeQuery(sql).getSingleResult();
				
		return Integer.valueOf(result.toString());
	}
}
