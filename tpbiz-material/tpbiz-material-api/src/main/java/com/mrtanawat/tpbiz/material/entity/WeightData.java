package com.mrtanawat.tpbiz.material.entity;

import java.math.BigDecimal;

public class WeightData {
	private long id;
	private String stat;
	private WeightingType weightingType;
	private Provider provider;
	private MaterialType materialType;
	private String remarks;
	private BigDecimal price;
	private ArrivalData arriveData;
	private ArrivalData leaveData;
	private WeightAdjustment weightAdjustment;
	private PriceAdjustment priceAdjustment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public WeightingType getWeightingType() {
		return weightingType;
	}
	public void setWeightingType(WeightingType weightingType) {
		this.weightingType = weightingType;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ArrivalData getArriveData() {
		return arriveData;
	}
	public void setArriveData(ArrivalData arriveData) {
		this.arriveData = arriveData;
	}
	public ArrivalData getLeaveData() {
		return leaveData;
	}
	public void setLeaveData(ArrivalData leaveData) {
		this.leaveData = leaveData;
	}
	public WeightAdjustment getWeightAdjustment() {
		return weightAdjustment;
	}
	public void setWeightAdjustment(WeightAdjustment weightAdjustment) {
		this.weightAdjustment = weightAdjustment;
	}
	public PriceAdjustment getPriceAdjustment() {
		return priceAdjustment;
	}
	public void setPriceAdjustment(PriceAdjustment priceAdjustment) {
		this.priceAdjustment = priceAdjustment;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
}
