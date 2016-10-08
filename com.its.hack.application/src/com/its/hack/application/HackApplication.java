package com.its.hack.application;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.its.hack.mastercard.api.locationservices.RequestConfig;
import com.its.hack.model.Merchant;
import com.mastercard.api.core.ApiConfig;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.places.MerchantCategoryCodes;

import osgi.enroute.configurer.api.RequireConfigurerExtender;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

@RequireAngularWebResource(resource = { "angular.js", "angular-resource.js", "angular-route.js" }, priority = 1000)
@RequireBootstrapWebResource(resource = "css/bootstrap.css")
@RequireWebServerExtender
@RequireConfigurerExtender
@Component(name = "com.its.hack")
public class HackApplication implements REST {

	private static String CONSUMER_KEY_VALUE = "0_9siZ0jCU9an62Wb7omXsjNGSE5McR8vkG0FT7G017df549!f380dfc0e8934003913a2351ff8f40190000000000000000";
	private static String KEY_ALIAS_VALUE = "its_hackathon_prod";
	private static String KEY_PASSWORD_VALUE = "priceless";
	private static String KEY_PATH_VALUE = "resource/its_hackathon_prod_production.p12";

	public String getUpper(String string) {

		return string.toUpperCase();

	}

	public List<Merchant> getMerchants() throws Exception {
		Merchant returnVal = new Merchant();
		returnVal.longitude = new BigDecimal(144.9631);
		returnVal.latitude = new BigDecimal(-37.8136);
		returnVal.industry = "test Industry";
		returnVal.name = "test name";
		
		// RequestConfig config = new RequestConfig(CONSUMER_KEY_VALUE,
		// KEY_ALIAS_VALUE, KEY_PASSWORD_VALUE, KEY_PATH_VALUE);
		// ApiConfig.setAuthentication(config.getAuthentication()); // You only
		// need to set this once
		// ApiConfig.setSandbox(false); // For production: use
		// ApiConfig.setSandbox(false);
		//
		// RequestMap map = new RequestMap();
		// map.set("Mcc_Codes", "true");
		//
		// MerchantCategoryCodes response = MerchantCategoryCodes.query(map);
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode-->0001
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName-->TAP
		// (PORTUGAL)
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode-->0002
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName-->ANSA
		// INTERNATIONAL
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode-->0003
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName-->CARLTON
		// HOTELS
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode-->0004
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName-->AIR
		// CARRIERS AIRLINES-NOT ELSEWHERE CLASSIFIED
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode-->0005
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName-->TRAVEL
		// AGENCIES AND TOUR OPERATORS
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode-->0006
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName-->UTLTS-ELCTRC
		// GAS HEATING OIL SANITARY WATER
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode-->0007
		// System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName"));
		// //
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName-->COMPUTERS
		// COMPUTER PERIPHERAL EQUIPMENT SOFTWARE
		//
		//
		//
		// // This sample shows looping through
		// MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode
		// List<Map<String, Object>> list = (List<Map<String, Object>>)
		// response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode");
		// for(int i = 0; i < list.size(); i++) {
		// System.out.println("index: " + i);
		// System.out.println("MerchantCatCode: [" +
		// list.get(i).get("MerchantCatCode") + "]");
		// System.out.println("MerchantCategoryName: [" +
		// list.get(i).get("MerchantCategoryName") + "]");
		//
		// }

		return Arrays.asList(returnVal);

	}

}
