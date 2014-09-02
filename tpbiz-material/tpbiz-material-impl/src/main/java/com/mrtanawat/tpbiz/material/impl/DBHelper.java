package com.mrtanawat.tpbiz.material.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class DBHelper {
	
	public static boolean isExistInDB(SimpleJdbcTemplate jdbcTemplate, String tableName, String whereColumnName, String whereValue)
	{
		String sql = "SELECT COUNT(*) FROM " + tableName +
				" WHERE "+ whereColumnName+ " = ?";
		int count = jdbcTemplate.queryForInt(sql, whereValue);
		if(count>0)
			return true;
		return false;
	}
	
}
