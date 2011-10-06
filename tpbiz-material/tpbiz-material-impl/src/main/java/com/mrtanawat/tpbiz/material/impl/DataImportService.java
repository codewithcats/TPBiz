package com.mrtanawat.tpbiz.material.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.sql.DataSource;

import nl.knaw.dans.common.dbflib.IfNonExistent;
import nl.knaw.dans.common.dbflib.Record;
import nl.knaw.dans.common.dbflib.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.mrtanawat.tpbiz.material.api.IDataImportService;

public class DataImportService implements IDataImportService {

	private SimpleJdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(DataImportService.class);

	@Override
	public void importMaterialType(File dbf) {
		Table productTypeTable = new Table(dbf);
		try {
			productTypeTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = productTypeTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String code = r.getStringValue("CODE").trim();
				String name = r.getStringValue("NAME").trim();
				String remark = r.getStringValue("REMARKS").trim();

				String insertSql = "INSERT INTO MATERIAL_TYPES(CODE, NAME, REMARKS) VALUE(?,?,?)";
				this.jdbcTemplate.update(insertSql, code, name, remark);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				productTypeTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void importReportType(File dbf) {
		Table reportsTable = new Table(dbf);
		try {
			reportsTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = reportsTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String code = r.getStringValue("CODE").trim();
				String name = r.getStringValue("NAME").trim();
				String remark = r.getStringValue("REMARKS").trim();
				String insertSql = "INSERT INTO MAT_REPORT_TYPES(CODE, NAME, REMARKS) VALUE(?,?,?)";
				this.jdbcTemplate.update(insertSql, code, name, remark);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				reportsTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}

	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public void importTruckType(File dbf) {
		Table truckTypeTable = new Table(dbf);
		try {
			truckTypeTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = truckTypeTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String code = r.getStringValue("CODE").trim();
				String name = r.getStringValue("NAME").trim();
				String remark = r.getStringValue("REMARKS").trim();
				String insertSql = "INSERT INTO MAT_TRUCK_TYPES(CODE, NAME, REMARKS) VALUE(?,?,?)";
				this.jdbcTemplate.update(insertSql, code, name, remark);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				truckTypeTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void importWeightType(File dbf) {
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = dbfTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String code = r.getStringValue("CODE").trim();
				String name = r.getStringValue("NAME").trim();
				String remark = r.getStringValue("REMARKS").trim();
				String insertSql = "INSERT INTO MAT_WEIGHT_TYPES(CODE, NAME, REMARKS) VALUE(?,?,?)";
				this.jdbcTemplate.update(insertSql, code, name, remark);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				dbfTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void importProviderData(File dbf) {
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = dbfTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String code = r.getStringValue("CODE").trim();
				String name = r.getStringValue("NAME").trim();
				String addr1 = r.getStringValue("ADDR1").trim();
				String addr2 = r.getStringValue("ADDR2").trim();
				String address = String.format("%1$s %2$s", addr1!=null?addr1:"", addr2!=null?addr2:"").trim();
				String place1 = r.getStringValue("PLACE1").trim();
				String place2 = r.getStringValue("PLACE2").trim();
				String place = String.format("%1$s %2$s", place1!=null?place1:"", place2!=null?place2:"");
				String remark = r.getStringValue("REMARKS");
				String insertSql = "INSERT INTO MAT_PROVIDERS(CODE, NAME, REMARKS, ADDRESS, PLACE) VALUE(?,?,?,?,?)";
				this.jdbcTemplate.update(insertSql, code, name, remark, address, place);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				dbfTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void importWeightData(File dbf) {
		Table dbfTable = new Table(dbf);
		try {
			dbfTable.open(IfNonExistent.ERROR);
			Iterator<Record> records = dbfTable.recordIterator();
			while (records.hasNext()) {
				Record r = records.next();
				String stat = r.getStringValue("STAT").trim();
				String truckLabel = r.getStringValue("TRUCK").trim();
				String wtype = r.getStringValue("CARTYPE").trim();
				int wtypeId = -1;
				try {
					wtypeId = "-".equals(wtype)?-1:this.jdbcTemplate.queryForInt("SELECT ID FROM MAT_WEIGHT_TYPES WHERE CODE = ?", wtype.trim());
				} catch (DataAccessException e) {
					logger.warn("Cannot find MAT_WEIGHT_TYPES with CODE = " + wtype);
				}
				String provider = r.getStringValue("COMPANY").trim();
				provider = StringUtils.leftPad(provider, 6, '0');
				int providerId = -1;
				try {
					providerId = "-".equals(provider)?-1:this.jdbcTemplate.queryForInt("SELECT ID FROM MAT_PROVIDERS WHERE CODE = ?", provider.trim());
				} catch (DataAccessException e) {
					logger.warn("Cannot find MAT_PROVIDERS with CODE = " + provider);
				}
				String material = r.getStringValue("PRODUCT").trim();
				int materialId = -1;
				try {
					materialId = "-".equals(material)?-1:this.jdbcTemplate.queryForInt("SELECT ID FROM MATERIAL_TYPES WHERE CODE = ?", material.trim());
				} catch (DataAccessException e) {
					logger.warn("Cannot find MATERIAL_TYPES with CODE = " + material);
				}
				String truckType = r.getStringValue("SUBCON").trim();
				int truckTypeId = -1;
				try {
					truckTypeId = "-".equals(truckType)?-1:this.jdbcTemplate.queryForInt("SELECT ID FROM MAT_TRUCK_TYPES WHERE CODE = ?", truckType.trim());
				} catch (DataAccessException e) {
					logger.warn("Cannot find MAT_TRUCK_TYPES with CODE = " + truckType);
				}
				String rm1 = r.getStringValue("REMARK1").trim();
				String rm2 = r.getStringValue("REMARK2").trim();
				String rm3 = r.getStringValue("REMARK3").trim();
				String remark = String.format("%1$s %2$s %3$s", rm1!=null?rm1:"", rm2!=null?rm2:"", rm3!=null?rm3:"").trim();
				Number priceNumber = r.getNumberValue("PRICE");
				BigDecimal price = new BigDecimal(priceNumber.doubleValue());
				String ticket1 = r.getStringValue("TICKET1");
				Calendar c = Calendar.getInstance();
				c.setTime(r.getDateValue("DAYIN"));
				String timeIn = r.getStringValue("TMIN");
				String isoDateIn = String.format("%1$s-%2$02d-%3$02d %4$s:00", 
						c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), timeIn);
				int weightIn = r.getNumberValue("W1").intValue();
				String ticket2 = r.getStringValue("TICKET2");
				Date dayOut = r.getDateValue("DAYOUT");
				String isoDateOut = null;
				if (dayOut != null) {
					c.setTime(dayOut);
					String timeOut = r.getStringValue("TMOUT");
					isoDateOut = String.format("%1$s-%2$02d-%3$02d %4$s:00", 
							c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), timeOut);
				}
				int weightOut = r.getNumberValue("W2").intValue();
				int wadjMoiture = r.getNumberValue("ADJ_W1").intValue();
				int wadjDilute = r.getNumberValue("ADJ_W2").intValue();
				int wadjOther = r.getNumberValue("ADJ_W3").intValue();
				int padjWeight = r.getNumberValue("ADJ_M1").intValue();
				int padjCarry = r.getNumberValue("ADJ_M2").intValue();
				int padjOther = r.getNumberValue("ADJ_M3").intValue();
				String insertSql = "INSERT INTO MAT_WEIGHT_DATA(STAT, TRUCK_LABEL, WEIGHT_TYPE_ID, PROVIDER_ID, MATERIAL_TYPE_ID, TRUCK_TYPE_ID, REMARKS, PRICE, TICKET_1, ARRIVE_DATE, ARRIVE_WEIGHT, TICKET_2, LEAVE_DATE, LEAVE_WEIGHT, WADJ_MOISTURE, WADJ_DILUTE, WADJ_OTHER, PADJ_WEIGHT, PADJ_CARRY, PADJ_OTHER) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				this.jdbcTemplate.update(insertSql, stat, truckLabel, wtypeId==-1?null:wtypeId, providerId==-1?null:providerId, materialId==-1?null:materialId, truckTypeId==-1?null:truckTypeId, remark, price, ticket1, isoDateIn, weightIn, ticket2, isoDateOut, weightOut, wadjMoiture, wadjDilute, wadjOther, padjWeight, padjCarry, padjOther);
			}
		} catch (Exception e) {
			// TODO: log
			throw new RuntimeException(e);
		} finally {
			try {
				dbfTable.close();
			} catch (IOException e) {
				// TODO: log
				throw new RuntimeException(e);
			}
		}
	}

}
