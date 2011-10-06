package com.mrtanawat.tpbiz.dbf;

import java.io.File;
import java.util.Set;

import com.mrtanawat.tpbiz.material.api.IProviderService;
import com.mrtanawat.tpbiz.material.entity.Provider;

public class ProviderDbfAlterationHandler implements DbfAlterationHandler {
	private IProviderService providerService;
	@Override
	public void onDbfChange(File dbf) {
		providerService.add(null);
	}
	
	public Set<Provider> getAlteredProviders(File dbf) {
		return null;
	}
	
	public void setProviderService(IProviderService providerService) {
		this.providerService = providerService;
	}

}
