package com.mrtanawat.tpbiz.material.impl;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import nl.knaw.dans.common.dbflib.CorruptedTableException;
import nl.knaw.dans.common.dbflib.IfNonExistent;
import nl.knaw.dans.common.dbflib.Table;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations="/beans.xml")
public class DataImportServiceTest 
	extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired private DataImportService dataImportService;
	
	@Test public void test_import_productTypes_from_dbf() throws IOException, CorruptedTableException {
		File productTypesDbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/product.DBF");
		dataImportService.importMaterialType(productTypesDbf);
		String countSql = "SELECT COUNT(*) FROM MATERIAL_TYPES";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(productTypesDbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_productTypes_using_nonExisted_dbf() {
		File productTypesDbf = new File("NonExisted.DBF");
		dataImportService.importMaterialType(productTypesDbf);
	}
	
	@Test public void test_import_report_from_dbf() throws IOException, CorruptedTableException {
		File reportDbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/replist.DBF");
		dataImportService.importReportType(reportDbf);
		String countSql = "SELECT COUNT(*) FROM MAT_REPORT_TYPES";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(reportDbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_reports_using_nonExisted_dbf() {
		File reportDbf = new File("NonExisted.DBF");
		dataImportService.importReportType(reportDbf);
	}
	
	@Test public void test_import_truckType_from_dbf() throws IOException, CorruptedTableException {
		File subconDbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/subcon.DBF");
		dataImportService.importTruckType(subconDbf);
		String countSql = "SELECT COUNT(*) FROM MAT_TRUCK_TYPES";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(subconDbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_truckType_using_nonExisted_dbf() {
		File dbf = new File("NonExisted.DBF");
		dataImportService.importTruckType(dbf);
	}
	
	@Test public void test_import_weightType_from_dbf() throws IOException, CorruptedTableException {
		File dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/cartype.DBF");
		dataImportService.importWeightType(dbf);
		String countSql = "SELECT COUNT(*) FROM MAT_WEIGHT_TYPES";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_weightType_using_nonExisted_dbf() {
		File dbf = new File("NonExisted.DBF");
		dataImportService.importWeightType(dbf);
	}
	
	@Test public void test_import_providers_from_dbf() throws IOException, CorruptedTableException {
		File dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/company.DBF");
		dataImportService.importProviderData(dbf);
		String countSql = "SELECT COUNT(*) FROM MAT_PROVIDERS";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_providers_using_nonExisted_dbf() {
		File dbf = new File("NonExisted.DBF");
		dataImportService.importProviderData(dbf);
	}
	
	@Test public void test_import_weightData_from_dbf() throws IOException, CorruptedTableException {
		File dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/product.DBF");
		dataImportService.importMaterialType(dbf);
		dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/subcon.DBF");
		dataImportService.importTruckType(dbf);
		dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/cartype.DBF");
		dataImportService.importWeightType(dbf);
		dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/company.DBF");
		dataImportService.importProviderData(dbf);
		dbf = new File("C:/Users/tnwt7/Documents/Tai-Phing Ethanol/TruckOem/dbftemp/wdata.dbf");
		dataImportService.importWeightData(dbf);
		String countSql = "SELECT COUNT(*) FROM MAT_WEIGHT_DATA";
		int dbCount = this.simpleJdbcTemplate.queryForInt(countSql);
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.IGNORE);
			int recordCount = dbfTable.getRecordCount();
			Assert.assertEquals("Every record must be inserted to database.", recordCount, dbCount);
		} finally {
			dbfTable.close();
		}
	}
	
	@ExpectedException(RuntimeException.class)
	@Test public void test_import_weightData_using_nonExisted_dbf() {
		File dbf = new File("NonExisted.DBF");
		dataImportService.importWeightData(dbf);
	}
}
