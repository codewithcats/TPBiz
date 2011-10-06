package com.mrtanawat.tpbiz.dbf.test;

import static org.easymock.EasyMock.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mrtanawat.tpbiz.dbf.DbfAlterationHandler;
import com.mrtanawat.tpbiz.dbf.DbfAlterationListener;

public class DbfAlterationListenerTest {
	private DbfAlterationListener listener;
	private File mockCartypeFile;
	private DbfAlterationHandler mockCartypeHandler;
	private File mockWeightDataFile;
	private DbfAlterationHandler mockWdataHandler;
	
	@Before public void before() {
		listener = new DbfAlterationListener();
		mockCartypeFile = createMock(File.class);
		mockCartypeHandler = createMock(DbfAlterationHandler.class);
		mockWeightDataFile = createMock(File.class);
		mockWdataHandler = createMock(DbfAlterationHandler.class);
		
		Map<String, DbfAlterationHandler> handlers = new HashMap<String, DbfAlterationHandler>();
		handlers.put("cartype.DBF", mockCartypeHandler);
		handlers.put("wdata.dbf", mockWdataHandler);
		
		listener.setHandlers(handlers);
	}
	
	@Test public void invokeCartypeDbfChangeHandlerAfterChanged() {
		expect(mockCartypeFile.getName()).andReturn("cartype.DBF").atLeastOnce();
		replay(mockCartypeFile);
		
		mockCartypeHandler.onDbfChange(mockCartypeFile);
		expectLastCall().once();
		replay(mockCartypeHandler);
		
		replay(mockWeightDataFile);
		replay(mockWdataHandler);
		
		listener.onFileChange(mockCartypeFile);
	}
	
	@Test public void invokeWdataDbfChangeHandlerAfterChanged() {
		expect(mockWeightDataFile.getName()).andReturn("wdata.dbf").atLeastOnce();
		replay(mockWeightDataFile);
		
		mockWdataHandler.onDbfChange(mockWeightDataFile);
		expectLastCall().once();
		replay(mockWdataHandler);
		
		replay(mockCartypeFile);
		replay(mockCartypeHandler);
		
		listener.onFileChange(mockWeightDataFile);
	}
	
	@Test public void noHandlerInvokedWhenNotRegisteredFileChanged() {
		replay(mockCartypeFile);
		replay(mockCartypeHandler);
		replay(mockWeightDataFile);
		replay(mockWdataHandler);
		
		File mockNotRegFile = createMock(File.class);
		expect(mockNotRegFile.getName()).andReturn("notReg.dbf").atLeastOnce();
		replay(mockNotRegFile);
		
		listener.onFileChange(mockNotRegFile);
		
		verify(mockNotRegFile);
	}
	
	@After public void after() {
		verify(mockWdataHandler);
		verify(mockWeightDataFile);
		verify(mockCartypeHandler);
		verify(mockCartypeFile);
	}

}
