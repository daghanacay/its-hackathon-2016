package com.its.hack.application;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import osgi.enroute.configurer.api.RequireConfigurerExtender;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

import com.its.hack.converter.MerchantPointOfInterestToMerchantListConverter;
import com.its.hack.mastercard.api.locationservices.RequestConfig;
import com.its.hack.model.Merchant;
import com.mastercard.api.core.ApiConfig;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.places.MerchantPointOfInterest;

@RequireAngularWebResource(resource = { "angular.js", "angular-resource.js", "angular-route.js" }, priority = 1000)
@RequireBootstrapWebResource(resource = "css/bootstrap.css")
@RequireWebServerExtender
@RequireConfigurerExtender
@Component(name = "com.its.hack")
public class HackApplication implements REST {

	private static String CONSUMER_KEY_VALUE 	= "0_9siZ0jCU9an62Wb7omXsjNGSE5McR8vkG0FT7G017df549!f380dfc0e8934003913a2351ff8f40190000000000000000";
	private static String KEY_ALIAS_VALUE 		= "its_hackathon_prod";
	private static String KEY_PASSWORD_VALUE 	= "priceless";
	private static String KEY_PATH_VALUE 		= "resource/its_hackathon_prod_production.p12";
	
	public String getUpper(String string) {
	
		return string.toUpperCase();
		
	}

	public List<Merchant> getMerchants(String lat, String lon) throws Exception
	{
		RequestConfig config = new RequestConfig(CONSUMER_KEY_VALUE, KEY_ALIAS_VALUE, KEY_PASSWORD_VALUE, KEY_PATH_VALUE);
        ApiConfig.setAuthentication(config.getAuthentication());
        ApiConfig.setSandbox(false);

        RequestMap map = new RequestMap();
        map.set("radiusSearch", "true");
        map.set("unit", "km");
        map.set("pageLength", "50");
        map.set("distance", "14");
        map.set("pageOffset", "0");
        map.set("place.countryCode", "AUS");
        map.set("place.latitude", lat);
        map.set("place.longitude", lon);

        MerchantPointOfInterest response = MerchantPointOfInterest.create(map);

        List<Merchant> merchantList = MerchantPointOfInterestToMerchantListConverter.convertMerchantPointOfInterestToMerchantList(response);

        return merchantList;
	}
}
