package com.its.hack.model;

import java.io.Serializable;

public class Merchant implements Serializable {

	private static final long serialVersionUID = 1L;

	public String merchantName; // MerchantPOIResponse.places.place[1].merchantName
	
	public Object latitude; // MerchantPOIResponse.places.place[0].latitude
	
	public Object longitude; // MerchantPOIResponse.places.place[0].longitude
	
	public String industry; // MerchantPOIResponse.places.place[0].industry
	// Remove this
	public double heat;

}
