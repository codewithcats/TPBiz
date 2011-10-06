package com.mrtanawat.tpbiz.material.api;

import java.io.File;

public interface IDataImportService {
	/**
	 * Import product types data from DBF file to configured database
	 * @param dbf
	 */
	public void importMaterialType(File dbf);
	/**
	 * Import reports data from DBF file to configured database
	 * @param dbf
	 */
	public void importReportType(File dbf);
	/**
	 * Import truck types data from DBF file to configured database
	 * @param dbf
	 */
	public void importTruckType(File dbf);
	/**
	 * Import weight types data from DBF file to configured database
	 * @param dbf
	 */
	public void importWeightType(File dbf);
	/**
	 * Import provider data from DBF file to configured database
	 * @param dbf
	 */
	public void importProviderData(File dbf);
	/**
	 * Import weight data from DBF file to configured database
	 * @param dbf
	 */
	public void importWeightData(File dbf);
}
