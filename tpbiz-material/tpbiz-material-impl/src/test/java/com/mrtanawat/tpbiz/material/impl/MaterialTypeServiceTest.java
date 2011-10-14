package com.mrtanawat.tpbiz.material.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.mrtanawat.tpbiz.material.api.IMaterialTypeService;
import com.mrtanawat.tpbiz.material.entity.MaterialType;

@ContextConfiguration(locations="/beans.xml")
public class MaterialTypeServiceTest 
		extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private IMaterialTypeService materialTypeService;
	@Autowired
	private MaterialRowMapper materialRowMapper;
	
	private static final String MATERAIL_TYPE_TABLE = "material_types";
	
	@Test
	public void addMaterialType() throws Exception {		
		
		String code = "Mat Code";
		String name = "Mat Name";
		String remarks = "Mat remark";
		MaterialType material = new MaterialType();
		material.setCode(code);
		material.setName(name);
		material.setRemarks(remarks);
		
		MaterialType addedMaterial = materialTypeService.add(material);
	
		String sql = "SELECT * FROM " + MATERAIL_TYPE_TABLE 
				+ " WHERE ID = ?";
		MaterialType materialTypeFromDb = simpleJdbcTemplate.queryForObject(sql, this.materialRowMapper, addedMaterial.getId());
	
		Assert.assertEquals(materialTypeFromDb.getId(), addedMaterial.getId());
		Assert.assertEquals(materialTypeFromDb.getCode(), code);
		Assert.assertEquals(materialTypeFromDb.getName(), name);
		Assert.assertEquals(materialTypeFromDb.getRemarks(), remarks);
	}
	
	@Test
	public void addMaterialTypeReturnWithId() throws Exception {		
		
		String code = "Mat Code";
		String name = "Mat Name";
		String remarks = "Mat remark";
		MaterialType material = new MaterialType();
		material.setCode(code);
		material.setName(name);
		material.setRemarks(remarks);
		
		MaterialType addedMaterial = materialTypeService.add(material);
	
		Assert.assertNotNull(addedMaterial.getId());
	}

	@Test
	@ExpectedException(com.mrtanawat.tpbiz.material.api.DuplicateCodeException.class)
	public void addMaterailWithExistingCode() throws Exception {
		String code = "Mat Code";
		String name = "Mat Name";
		String remarks = "Mat remark";
		String sql = "INSERT INTO "+ MATERAIL_TYPE_TABLE 
				+" (CODE, NAME, REMARKS) VALUES (?,?,?)";
		simpleJdbcTemplate.update(sql, code,
										name,
										remarks);
		
		MaterialType material = new MaterialType();
		material.setCode(code);
		material.setName(name);
		material.setRemarks(remarks);
		
		materialTypeService.add(material);		
	}
	
	@Test
	public void updateMaterialType() throws Exception {
		String code = "Mat Code";
		String name = "Mat Name";
		String remarks = "Mat remark";
		
		String sql = "INSERT INTO "+ MATERAIL_TYPE_TABLE 
				+" (CODE, NAME, REMARKS) VALUES (?,?,?)";
		simpleJdbcTemplate.update(sql, code,
										name,
										remarks);
		
		sql = "SELECT * FROM " + MATERAIL_TYPE_TABLE 
				+ " WHERE ID = LAST_INSERT_ID()";
		MaterialType materialTypeFromDb = simpleJdbcTemplate.queryForObject(sql, this.materialRowMapper);
		
		String newName = "New Mat Name";
		String newRemarks = "New Mat remark";
		
		materialTypeFromDb.setName(newName);
		materialTypeFromDb.setRemarks(newRemarks);
		materialTypeService.update(materialTypeFromDb);
		
		Assert.assertEquals(materialTypeFromDb.getName(), newName);
		Assert.assertNotSame(materialTypeFromDb.getName(), name);
		
		Assert.assertEquals(materialTypeFromDb.getRemarks(), newRemarks);
		Assert.assertNotSame(materialTypeFromDb.getRemarks(), remarks);		
	}
	
	@Test
	@ExpectedException(com.mrtanawat.tpbiz.material.api.EntityNotExistException.class)
	public void updateMaterialTypeNotExist() throws Exception{
		String code = "Mat Code";
		String name = "Mat Name";
		String remarks = "Mat remark";
		
		MaterialType material = new MaterialType();
		material.setCode(code);
		material.setName(name);
		material.setRemarks(remarks);
		
		materialTypeService.update(material);
	}
	
	@Test
	public void listMaterialType() throws Exception{
		int materialTypeCount = 10;
		List<MaterialType> mockList = createMaterialTypeMockList(materialTypeCount); 
		for(MaterialType type: mockList)
		{
			String sql = "INSERT INTO "+ MATERAIL_TYPE_TABLE 
					+" (CODE, NAME, REMARKS) VALUES (?,?,?)";
			simpleJdbcTemplate.update(sql, type.getCode(),
											type.getName(),
											type.getRemarks());			
		}
		
		List<MaterialType> listFromDB = materialTypeService.list(0, materialTypeCount);
		Assert.assertEquals(listFromDB.size(),materialTypeCount);
		
		//iterate through all the item in the list
		for(MaterialType typeInDB: listFromDB)
		{
			Assert.assertTrue(listContainMaterialType(mockList, typeInDB));
		}
		
	}
	
	private Boolean listContainMaterialType(List<MaterialType> typeList, MaterialType type)
	{
		for(MaterialType listItem: typeList)
		{
			if(compareMaterialType(listItem,type))
			{
				return true;
			}
		}
		return false;
	}
	
	private Boolean compareMaterialType(MaterialType expect,MaterialType actual){
		if(expect.getCode().equals(actual.getCode())
				&& expect.getName().equals(actual.getName())
				&& expect.getRemarks().equals(actual.getRemarks()))
		{
			return true;
		}
		return false;
	}
	
	private List<MaterialType> createMaterialTypeMockList(int count){
		List<MaterialType> materialList = new ArrayList<MaterialType>();
		for(int i=0 ; i<count ; i++)
		{
			materialList.add(createMockMaterialTypeNoId( Integer.toString(i) ));	
		}
		return materialList;
	}

	private MaterialType createMockMaterialTypeNoId(String uniqueString) {
		String code = "Mat Code" + uniqueString;
		String name = "Mat Name" + uniqueString;
		String remarks = "Mat remark" + uniqueString;
		
		MaterialType material = new MaterialType();
		material.setCode(code);
		material.setName(name);
		material.setRemarks(remarks);
		return material;
	}

}
