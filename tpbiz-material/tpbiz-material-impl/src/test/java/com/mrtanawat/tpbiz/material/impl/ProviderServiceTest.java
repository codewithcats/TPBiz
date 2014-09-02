package com.mrtanawat.tpbiz.material.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.entity.Provider;
import com.mrtanawat.tpbiz.material.impl.ProviderRowMapper;

@ContextConfiguration(locations="/beans.xml")
public class ProviderServiceTest 	
	extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private IProviderService providerService;
	@Autowired
	private ProviderRowMapper providerRowMapper;
	
	private static final String PROVIDER_TABLE = "mat_providers";
	
	@Test
	public void findByCodeWithExistingCode() throws Exception {
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		String sql = "INSERT  INTO "+ PROVIDER_TABLE 
				+" (CODE, NAME, ADDRESS, PLACE, REMARKS) VALUES (?,?,?,?,?)";
		simpleJdbcTemplate.update(sql, code,
										name,
										address,
										place,
										remark);
	
		Provider foundedProvider = providerService.findByCode(code);
		Assert.assertEquals(foundedProvider.getCode(), code);
		Assert.assertEquals(foundedProvider.getName(), name);
		Assert.assertEquals(foundedProvider.getAddress(), address);
		Assert.assertEquals(foundedProvider.getPlace(), place);
		Assert.assertEquals(foundedProvider.getRemark(), remark);
	}
	
	@Test
	@ExpectedException(Exception.class)
	public void findByCodeWithNotExistingCode() throws Exception {
		String code = "notExistCode";	
		providerService.findByCode(code);
	}
	
	@Test
	public void addProvider() throws Exception {		
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		Provider provider = new Provider();
		provider.setCode(code);
		provider.setName(name);
		provider.setAddress(address);
		provider.setPlace(place);
		provider.setRemark(remark);
		
		Provider addedProvider = providerService.add(provider);
	
		String sql = "SELECT * FROM " + PROVIDER_TABLE 
				+ " WHERE ID = ?";
		Provider providerFromDb = simpleJdbcTemplate.queryForObject(sql, this.providerRowMapper, addedProvider.getId());
	
		Assert.assertEquals(providerFromDb.getId(), addedProvider.getId());
		Assert.assertEquals(providerFromDb.getCode(), code);
		Assert.assertEquals(providerFromDb.getName(), name);
		Assert.assertEquals(providerFromDb.getAddress(), address);
		Assert.assertEquals(providerFromDb.getPlace(), place);
		Assert.assertEquals(providerFromDb.getRemark(), remark);
	}
	
	@Test
	public void addProviderReturnWithId() throws Exception {		
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		Provider provider = new Provider();
		provider.setCode(code);
		provider.setName(name);
		provider.setAddress(address);
		provider.setPlace(place);
		provider.setRemark(remark);
		
		Provider addedProvider = providerService.add(provider);
	
		Assert.assertNotNull(addedProvider.getId());
	}
	
	@Test
	@ExpectedException(com.mrtanawat.tpbiz.material.api.DuplicateCodeException.class)
	public void addProviderWithExistingCode() throws Exception {
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		String sql = "INSERT  INTO "+ PROVIDER_TABLE 
				+" (CODE, NAME, ADDRESS, PLACE, REMARKS) VALUES (?,?,?,?,?)";
		simpleJdbcTemplate.update(sql, code,
										name,
										address,
										place,
										remark);
		
		Provider provider = new Provider();
		provider.setCode(code);
		provider.setName(name);
		provider.setAddress(address);
		provider.setPlace(place);
		provider.setRemark(remark);
		
		providerService.add(provider);
	}
	
	@Test
	public void updateProvider() throws Exception {
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		String sql = "INSERT  INTO "+ PROVIDER_TABLE 
				+" (CODE, NAME, ADDRESS, PLACE, REMARKS) VALUES (?,?,?,?,?)";
		simpleJdbcTemplate.update(sql, code,
										name,
										address,
										place,
										remark);
		
		sql = "SELECT * FROM " + PROVIDER_TABLE 
				+ " WHERE ID = LAST_INSERT_ID()";
		Provider provider = simpleJdbcTemplate.queryForObject(sql, this.providerRowMapper);
		
		String newName = "New Name";		
		String newAddress = "New 123 Test Road Test District";
		String newPlace = "New Test Place";
		String newRemark = "New Test Remarks";
		
		provider.setName(newName);
		provider.setAddress(newAddress);
		provider.setPlace(newPlace);
		provider.setRemark(newRemark);
		providerService.update(provider);
		
		Assert.assertEquals(provider.getName(), newName);
		Assert.assertNotSame(provider.getName(), name);
		
		Assert.assertEquals(provider.getAddress(), newAddress);
		Assert.assertNotSame(provider.getAddress(), address);
		
		Assert.assertEquals(provider.getPlace(), newPlace);
		Assert.assertNotSame(provider.getPlace(), place);
		
		Assert.assertEquals(provider.getRemark(), newRemark);
		Assert.assertNotSame(provider.getRemark(), remark);		
	}
	
	@Test
	@ExpectedException(com.mrtanawat.tpbiz.material.api.EntityNotExistException.class)
	public void updateProviderNotExist() throws Exception {
		String code = "codeTest";
		String name = "nameTest";
		String address = "123 Test Road Test District";
		String place = "Test Place";
		String remark = "Test Remarks";
		
		Provider provider = new Provider();
		provider.setCode(code);
		provider.setName(name);
		provider.setAddress(address);
		provider.setPlace(place);
		provider.setRemark(remark);
		providerService.update(provider);
	}

}
