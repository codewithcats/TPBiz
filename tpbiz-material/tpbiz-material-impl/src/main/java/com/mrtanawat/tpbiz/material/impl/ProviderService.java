package com.mrtanawat.tpbiz.material.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import javax.sql.DataSource;

import com.mrtanawat.tpbiz.material.api.DuplicateCodeException;
import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.api.EntityNotExistException;
import com.mrtanawat.tpbiz.material.entity.Provider;

public class ProviderService implements IProviderService {
	
	private SimpleJdbcTemplate jdbcTemplate;
	private ProviderRowMapper providerRowMapper;
	private static final String PROVIDER_TABLE = "mat_providers";
	
	public ProviderService()
	{
		this.providerRowMapper = new ProviderRowMapper();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public Provider findByCode(String code) {		
		String sql = "SELECT * FROM " +PROVIDER_TABLE+ " WHERE CODE = ?";		
		Provider provider = jdbcTemplate.queryForObject(sql, this.providerRowMapper, code);
		return provider;
	}

	@Override
	public Provider add(Provider provider) throws DuplicateCodeException {
		
		if(DBHelper.isExistInDB(this.jdbcTemplate,PROVIDER_TABLE,"CODE",provider.getCode()))
			throw new DuplicateCodeException();
		
		String sql = "INSERT INTO "+ PROVIDER_TABLE 
				+" (CODE, NAME, ADDRESS, PLACE, REMARKS) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, provider.getCode(),
									provider.getName(),
									provider.getAddress(),
									provider.getPlace(),
									provider.getRemark());
		
		sql = "SELECT * FROM " + PROVIDER_TABLE 
				+ " WHERE ID = LAST_INSERT_ID()";
		provider = jdbcTemplate.queryForObject(sql, this.providerRowMapper);
		return provider;
	}

	@Override
	public void update(Provider provider) throws EntityNotExistException {
		//TODO do I need to check code? cause code not suppose to be able to change 
		long providerId = provider.getId();
		if(!DBHelper.isExistInDB(this.jdbcTemplate,PROVIDER_TABLE,"ID",String.valueOf(providerId)))
			throw new EntityNotExistException();
		
		String sql = "UPDATE " + PROVIDER_TABLE + 
				" SET NAME = ?, ADDRESS= ?, PLACE = ?, REMARKS = ? " +
				" WHERE ID = ?";
		
		jdbcTemplate.update(sql, provider.getName(),
								provider.getAddress(),
								provider.getPlace(),
								provider.getRemark(),
								providerId);
	}
}
