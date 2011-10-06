package com.mrtanawat.tpbiz.dbf;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
/**
 * Listen for .DBF alteration event and then invoke appropriate handler
 * @author tnwt7
 *
 */
public class DbfAlterationListener extends FileAlterationListenerAdaptor {
	/**
	 * key = file name, value = handler instance
	 */
	private Map<String, DbfAlterationHandler> handlers;
	
	@Override
	public void onFileChange(File file) {
		if(!handlers.containsKey(file.getName())) return;
		DbfAlterationHandler handler = handlers.get(file.getName());
		handler.onDbfChange(file);
	}
	
	public void setHandlers(Map<String, DbfAlterationHandler> handlers) {
		this.handlers = handlers;
	}
}
