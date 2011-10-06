package com.mrtanawat.tpbiz.material.entity;

import java.math.BigDecimal;

public class PriceAdjustment {
	private long id;
	private BigDecimal weight;
	private BigDecimal carry;
	private BigDecimal other;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getCarry() {
		return carry;
	}
	public void setCarry(BigDecimal carry) {
		this.carry = carry;
	}
	public BigDecimal getOther() {
		return other;
	}
	public void setOther(BigDecimal other) {
		this.other = other;
	}
}
