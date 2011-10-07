package com.mrtanawat.tpbiz.material.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mrtanawat.tpbiz.material.entity.Provider;

public class ProviderRowMapper implements RowMapper<Provider> {

	@Override
	public Provider mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
		Provider provider = new Provider();
		provider.setId(resultSet.getInt("ID"));
		provider.setCode(resultSet.getString("CODE"));
		provider.setName(resultSet.getString("NAME"));
		provider.setAddress(resultSet.getString("ADDRESS"));
		provider.setPlace(resultSet.getString("PLACE"));
		provider.setRemark(resultSet.getString("REMARKS"));
		return provider;
	}

}
