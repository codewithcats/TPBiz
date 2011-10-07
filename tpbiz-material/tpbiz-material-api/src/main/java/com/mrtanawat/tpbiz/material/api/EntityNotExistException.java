package com.mrtanawat.tpbiz.material.api;

/**
 * Thrown when cannot find the entity in underlying database
 * @author tnwt7
 *
 */
public class EntityNotExistException extends Exception {
	private static final long serialVersionUID = -1640833680772453079L;
	
	private Object entity;
	
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public Object getEntity() {
		return entity;
	}
}
