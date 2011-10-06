package com.mrtanawat.tpbiz.material.entity;

public class Truck {
	private long id;
	private String label;
	private TruckType type;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public TruckType getType() {
		return type;
	}
	public void setType(TruckType type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
