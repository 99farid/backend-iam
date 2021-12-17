package com.lawencon.assetsmanagement.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.assetsmanagement.dao.ItemTypesDao;
import com.lawencon.assetsmanagement.model.Companies;
import com.lawencon.assetsmanagement.model.ItemTypes;
import com.lawencon.base.BaseDaoImpl;

@Repository
public class ItemTypesDaoImpl extends BaseDaoImpl<ItemTypes> implements ItemTypesDao {

	@Override
	public List<ItemTypes> findAll() throws Exception {
		return getAll();
	}

	@Override
	public ItemTypes findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ItemTypes saveOrUpdate(ItemTypes data) throws Exception {
		return save(data);
	}

	@Override
	public boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<ItemTypes> findAll(String code) throws Exception {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT id, code, item_type_name, ver, created_by, created_date, updated_by, updated_date, is_active ");
		queryBuilder.append("FROM item_types ");
		queryBuilder.append("WHERE code LIKE :code");
		
		String sql = queryBuilder.toString();
		List<?> result = createNativeQuery(sql).setParameter("code", code).getResultList();
		List<ItemTypes> resultList = new ArrayList<>();
		
		result.forEach(rs -> {
			Object[] arrObj = (Object[]) rs;
			ItemTypes types = new ItemTypes();
			types.setId(arrObj[0].toString());
			types.setCode(arrObj[1].toString());
			types.setItemTypeName(arrObj[2].toString());
			types.setVersion(Long.valueOf(arrObj[3].toString()));
			types.setCreatedBy(arrObj[4].toString());
			types.setCreatedDate(((Timestamp)arrObj[5]).toLocalDateTime());
			if(arrObj[6] != null) {
				types.setUpdatedBy(arrObj[6].toString());
			}
			if(arrObj[7] != null) {
				types.setUpdatedDate(((Timestamp)arrObj[7]).toLocalDateTime());
			}
			types.setIsActive((Boolean) arrObj[8]);
			
			resultList.add(types);
		});
		return resultList;
	}

}
