package com.its.hack.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.its.hack.model.Merchant;
import com.mastercard.api.places.MerchantPointOfInterest;

public class MerchantPointOfInterestToMerchantListConverter {

	public static List<Merchant> convertMerchantPointOfInterestToMerchantList(MerchantPointOfInterest merchantPointOfInterest){

		List<Map<String, Object>> rawMerchantDataList = (List<Map<String, Object>>) merchantPointOfInterest.get("MerchantPOIResponse.places.place");

		List<Merchant> merchantList = new ArrayList<Merchant>();

		if (rawMerchantDataList==null || rawMerchantDataList.isEmpty())
		{
			return Collections.<Merchant>emptyList();
		}

		for (Map<String, Object> rawMerchantData: rawMerchantDataList)
		{
			Merchant merchant = new Merchant();
			merchant.merchantName = rawMerchantData.get("merchantName").toString();
			merchant.latitude = new BigDecimal(rawMerchantData.get("latitude").toString());
			merchant.longitude = new BigDecimal(rawMerchantData.get("longitude").toString());
			merchant.industry = rawMerchantData.get("industry").toString();
			merchantList.add(merchant);
		}

		return merchantList;
	}
}
