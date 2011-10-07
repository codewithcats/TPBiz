package com.mrtanawat.tpbiz.material.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.entity.Provider;

@ContextConfiguration(locations="/beans.xml")
public class ProviderServiceTest 	
	extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private IProviderService providerService;
	
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
	public void findByCodeWithNotExistingCode() throws Exception {
		Assert.fail();
	}
		

}
