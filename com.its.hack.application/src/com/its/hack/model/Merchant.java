package com.its.hack.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Merchant implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private String industry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
