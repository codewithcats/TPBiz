package com.mrtanawat.tpbiz.material.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.mrtanawat.tpbiz.material.entity.MaterialType;

public class MaterialRowMapper implements RowMapper<MaterialType> {

	@Override
	public MaterialType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		MaterialType materialType = new MaterialType();
		materialType.setId(resultSet.getInt("ID"));
		materialType.setCode(resultSet.getString("CODE"));
		materialType.setName(resultSet.getString("NAME"));
		materialType.setRemarks(resultSet.getString("REMARKS"));
		return materialType;
	}

}
