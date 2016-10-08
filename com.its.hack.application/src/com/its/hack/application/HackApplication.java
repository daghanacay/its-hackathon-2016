package com.its.hack.application;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import osgi.enroute.configurer.api.RequireConfigurerExtender;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

import com.mastercard.api.core.ApiConfig;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.core.security.oauth.OAuthAuthentication;
import com.mastercard.api.places.MerchantCategoryCodes;

@RequireAngularWebResource(resource={"angular.js","angular-resource.js", "angular-route.js"}, priority=1000)
@RequireBootstrapWebResource(resource="css/bootstrap.css")
@RequireWebServerExtender
@RequireConfigurerExtender
@Component(name="com.its.hack")
public class HackApplication implements REST {

	private static String CONSUMER_KEY_VALUE 	= "0_9siZ0jCU9an62Wb7omXsjNGSE5McR8vkG0FT7G017df549!f380dfc0e8934003913a2351ff8f40190000000000000000";
	private static String KEY_ALIAS_VALUE 		= "its_hackathon_prod";
	private static String KEY_PASSWORD_VALUE 	= "priceless";
	private static String KEY_PATH_VALUE 		= "resource/its_hackathon_prod_production.p12";
	
	public String getUpper(String string) {
	
		return string.toUpperCase();
		
	}
	
	public void getMerchants() throws Exception
	{
        String consumerKey = CONSUMER_KEY_VALUE;   // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
        String keyAlias = KEY_ALIAS_VALUE;   // For production: change this to the key alias you chose when you created your production key
        String keyPassword = KEY_PASSWORD_VALUE;   // For production: change this to the key alias you chose when you created your production key
        InputStream is = new FileInputStream(KEY_PATH_VALUE); // e.g. /Users/yourname/project/sandbox.p12 | C:\Users\yourname\project\sandbox.p12
        ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));   // You only need to set this once

        ApiConfig.setSandbox(false);     // For production: use ApiConfig.setSandbox(false);

        RequestMap map = new RequestMap();
        map.set("Mcc_Codes", "true");
        
        MerchantCategoryCodes response = MerchantCategoryCodes.query(map);
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCatCode-->0001
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[0].MerchantCategoryName-->TAP (PORTUGAL)
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCatCode-->0002
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[1].MerchantCategoryName-->ANSA INTERNATIONAL
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCatCode-->0003
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[2].MerchantCategoryName-->CARLTON HOTELS
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCatCode-->0004
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[3].MerchantCategoryName-->AIR CARRIERS  AIRLINES-NOT ELSEWHERE CLASSIFIED
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCatCode-->0005
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[4].MerchantCategoryName-->TRAVEL AGENCIES AND TOUR OPERATORS
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCatCode-->0006
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[5].MerchantCategoryName-->UTLTS-ELCTRC  GAS  HEATING OIL  SANITARY  WATER
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCatCode-->0007
        System.out.println("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName-->"+response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName")); // MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode[6].MerchantCategoryName-->COMPUTERS  COMPUTER PERIPHERAL EQUIPMENT  SOFTWARE
        

        
        // This sample shows looping through MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode
        List<Map<String, Object>> list = (List<Map<String, Object>>) response.get("MerchantCategoryCodeList.MerchantCategoryCodeArray.MerchantCategoryCode");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("index: " + i);
            System.out.println("MerchantCatCode: [" + list.get(i).get("MerchantCatCode") + "]");
            System.out.println("MerchantCategoryName: [" + list.get(i).get("MerchantCategoryName") + "]");
            
        }
        
    
	}

}
