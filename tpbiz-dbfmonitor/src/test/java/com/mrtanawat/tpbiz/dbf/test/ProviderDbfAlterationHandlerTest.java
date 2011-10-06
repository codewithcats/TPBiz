package com.mrtanawat.tpbiz.dbf.test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.security.jca.Providers;

import com.mrtanawat.tpbiz.dbf.ProviderDbfAlterationHandler;
import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.entity.Provider;

public class ProviderDbfAlterationHandlerTest {
	private ProviderDbfAlterationHandler providerDbfAlterationHandler;
	
	@Before public void before() {
		this.providerDbfAlterationHandler = new ProviderDbfAlterationHandler();
	}
	
	@Test public void newProviderAdded() {
		File mockDbf = createMock(File.class);
		IProviderService providerService = createMock(IProviderService.class);
		this.providerDbfAlterationHandler.setProviderService(providerService);
		
		expect(providerService.add(null)).andReturn(null).once();
		replay(providerService);
		
		this.providerDbfAlterationHandler.onDbfChange(mockDbf);
		verify(providerService);
	}
	
	@Test public void alteredExistProvider() {
		fail("not yet implemented");
	}
	
	@Test public void doNothing() {
		fail("not yet implemented");
	}
	
	@Test public void exceptionWhileReadDbf() {
		fail("not yet implemented");
	}
	
	@After public void after() {}
}
