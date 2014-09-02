package com.mrtanawat.tpbiz.material.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.mrtanawat.tpbiz.material.api.DuplicateCodeException;
import com.mrtanawat.tpbiz.material.api.EntityNotExistException;
import com.mrtanawat.tpbiz.material.api.IMaterialTypeService;
import com.mrtanawat.tpbiz.material.entity.MaterialType;

public class MaterialTypeService implements IMaterialTypeService {

	private SimpleJdbcTemplate jdbcTemplate;
	private MaterialRowMapper materialRowMapper;
	private static final String MATERAIL_TYPE_TABLE = "material_types";
	
	public MaterialTypeService()
	{
		this.materialRowMapper = new MaterialRowMapper();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public MaterialType add(MaterialType type) throws DuplicateCodeException{
		if(DBHelper.isExistInDB(this.jdbcTemplate,MATERAIL_TYPE_TABLE,"CODE",type.getCode()))
			throw new DuplicateCodeException();
		
		String sql = "INSERT INTO "+ MATERAIL_TYPE_TABLE 
				+" (CODE, NAME, REMARKS) VALUES (?,?,?)";
		jdbcTemplate.update(sql, type.getCode(),
								type.getName(),
								type.getRemarks());
		
		sql = "SELECT * FROM " + MATERAIL_TYPE_TABLE 
				+ " WHERE ID = LAST_INSERT_ID()";
		type = jdbcTemplate.queryForObject(sql, this.materialRowMapper);
		return type;
	}

	@Override
	public void update(MaterialType type) throws EntityNotExistException {
		long typeId = type.getId();
		if(!DBHelper.isExistInDB(this.jdbcTemplate,MATERAIL_TYPE_TABLE,"ID",String.valueOf(typeId)))
			throw new EntityNotExistException();
		
		String sql = "UPDATE " + MATERAIL_TYPE_TABLE + 
				" SET NAME = ?, REMARKS = ? " +
				" WHERE ID = ?";
		
		jdbcTemplate.update(sql, type.getName(),
								type.getRemarks(),
								typeId);

	}

	@Override
	public List<MaterialType> list(int start, int limit) {
		String sql = "SELECT * FROM " + MATERAIL_TYPE_TABLE + 
				" LIMIT ?, ? ";
		
		return jdbcTemplate.query(sql,this.materialRowMapper, 
				start,
				limit);

	}

}
