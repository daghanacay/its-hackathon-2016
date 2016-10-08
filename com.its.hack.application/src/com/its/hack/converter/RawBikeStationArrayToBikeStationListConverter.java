package com.its.hack.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.its.hack.model.BikeStation;

public class RawBikeStationArrayToBikeStationListConverter {

	public static List<BikeStation> convertRawBikeStationArrayToBikeStationList(JSONArray rawBikeStationArray){

		List<BikeStation> bikeStationList = new ArrayList<BikeStation>();

		if (rawBikeStationArray==null || rawBikeStationArray.isEmpty())
		{
			return Collections.<BikeStation>emptyList();
		}

		for (Object rawBikeStation: rawBikeStationArray)
		{
			JSONObject rawBikeStationJSON = (JSONObject) rawBikeStation;
			BikeStation bikeStation = new BikeStation();
			bikeStation.name = (String) rawBikeStationJSON.get("name");
			bikeStation.latitude = new BigDecimal(rawBikeStationJSON.get("latitude").toString());
			bikeStation.longitude = new BigDecimal(rawBikeStationJSON.get("longitude").toString());
			bikeStation.emptySlots = new Integer(rawBikeStationJSON.get("empty_slots").toString());
			bikeStation.freeBikes = new Integer(rawBikeStationJSON.get("free_bikes").toString());
			bikeStationList.add(bikeStation);
		}
		return bikeStationList;
	}
}
