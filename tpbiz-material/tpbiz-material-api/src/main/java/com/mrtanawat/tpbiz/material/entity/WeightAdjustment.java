package com.mrtanawat.tpbiz.material.entity;

public class WeightAdjustment {
	private long id;
	private float moisture;
	private float dilute;
	private float other;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getMoisture() {
		return moisture;
	}
	public void setMoisture(float moisture) {
		this.moisture = moisture;
	}
	public float getDilute() {
		return dilute;
	}
	public void setDilute(float dilute) {
		this.dilute = dilute;
	}
	public float getOther() {
		return other;
	}
	public void setOther(float other) {
		this.other = other;
	}
}
