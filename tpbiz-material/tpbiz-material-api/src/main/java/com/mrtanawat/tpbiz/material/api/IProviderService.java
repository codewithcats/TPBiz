package com.mrtanawat.tpbiz.material.api;

import com.mrtanawat.tpbiz.material.entity.Provider;

public interface IProviderService {
	/**
	 * Find existing provider using code
	 * @param code
	 * @return provider, if exist. If not, return null.
	 */
	public Provider findByCode(String code);
	/**
	 * Add new provider
	 * @param provider
	 * @return newly added provide with id injected
	 */
	public Provider add(Provider provider);
	/**
	 * Update provider
	 * @param provider
	 * @throws ProviderNotExistException when cannot find provider
	 */
	public void update(Provider provider) throws ProviderNotExistException;
}
