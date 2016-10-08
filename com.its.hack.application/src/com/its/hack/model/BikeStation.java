package com.its.hack.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BikeStation implements Serializable {

	private static final long serialVersionUID = 6048667386733410017L;

	public BigDecimal latitude;
	
	public BigDecimal longitude;
	
	public String name;
	
	public int emptySlots;
	
	public int freeBikes;
}
