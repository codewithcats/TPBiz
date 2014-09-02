package com.mrtanawat.tpbiz.material.api;

import java.util.List;

import com.mrtanawat.tpbiz.material.entity.MaterialType;

public interface IMaterialTypeService {
	/**
	 * Add new material type
	 * @param type
	 * @return newly added material type with id injected
	 */
	public MaterialType add(MaterialType type) throws DuplicateCodeException;
	/**
	 * Update existing material type
	 * @param type
	 * @throws EntityNotExistException
	 */
	public void update(MaterialType type) throws EntityNotExistException;
	/**
	 * List all material type in database
	 * @param start start index
	 * @param limit maximum number of returned material type
	 * @return list of material type
	 */
	public List<MaterialType> list(int start, int limit);
}
