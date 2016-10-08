package com.its.hack.application;

import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.osgi.service.component.annotations.Component;

import com.its.hack.converter.MerchantPointOfInterestToMerchantListConverter;
import com.its.hack.mastercard.api.locationservices.RequestConfig;
import com.its.hack.model.BikeStation;
import com.its.hack.model.Merchant;
import com.its.hack.traffic.api.HttpRequestController;
import com.its.hack.traffic.api.TrafficAPIConstants;
import com.mastercard.api.core.ApiConfig;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.places.MerchantPointOfInterest;

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
	
	public List<BikeStation> getBikeStations() 
	{
		HttpRequestController controller = new HttpRequestController();
		try 
		{
			HttpResponse response = controller.getRequest(TrafficAPIConstants.URL, TrafficAPIConstants.JSON_HEADER);
						
			if ((response!= null) &&  response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			InputStream input = response.getEntity().getContent();
			String responseResult = controller.convertStreamToString(input);
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject)parser.parse(responseResult);
			JSONObject networkJson = (JSONObject)json.get(TrafficAPIConstants.NETWORK_KEY);
			JSONArray stationJsonArray = (JSONArray)networkJson.get(TrafficAPIConstants.STATIONS_KEY);
			Iterator stationIterator = stationJsonArray.iterator();
			while (stationIterator.hasNext()) 
			{
			  JSONObject stationJson = (JSONObject)stationIterator.next();
			  System.out.println(stationJson.get("empty_slots"));
			}
		}
		catch (Exception ex)
		{
			System.out.println("GET bike stations failed: " + ex.getMessage());
		}
		finally {
			controller.close();
		}
		
		return Collections.<BikeStation>emptyList();
	}
}
