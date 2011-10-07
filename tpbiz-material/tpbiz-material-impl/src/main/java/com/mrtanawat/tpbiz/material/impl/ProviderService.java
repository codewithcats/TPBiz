package com.mrtanawat.tpbiz.material.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import javax.sql.DataSource;

import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.api.ProviderNotExistException;
import com.mrtanawat.tpbiz.material.entity.Provider;

public class ProviderService implements IProviderService {
	
	private SimpleJdbcTemplate jdbcTemplate;
	private ProviderRowMapper providerRowMapper;
	
	public ProviderService()
	{
		this.providerRowMapper = new ProviderRowMapper();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public Provider findByCode(String code) {		
		String sql = "SELECT * FROM mat_providers WHERE CODE = ?";		
		Provider provider = jdbcTemplate.queryForObject(sql, this.providerRowMapper, code);
		return provider;
	}

	@Override
	public Provider add(Provider provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Provider provider) throws ProviderNotExistException {
		// TODO Auto-generated method stub

	}

}
