package com.clickntap.nmea;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class NMEAUtils {

	public static double parseLatitude(String lat, String cp) {
		double value = Double.parseDouble(lat.substring(2)) / 60.0;
		value += Double.parseDouble(lat.substring(0, 2));
		if (cp.startsWith("S")) {
			value = -value;
		}
		DecimalFormat f = new DecimalFormat("##.000000");
		return Double.parseDouble(f.format(value));
	}

	public static double parseLongitude(String lon, String cp) {
		double value = Double.parseDouble(lon.substring(3)) / 60.0;
		value += Double.parseDouble(lon.substring(0, 3));
		if (cp.startsWith("W")) {
			value = -value;
		}
		DecimalFormat f = new DecimalFormat("##.000000");
		return Double.parseDouble(f.format(value));
	}

	public static Position parsePosition(JSONObject info) {
		if (info.has("info")) {
			info = info.getJSONObject("info");
		}
		Position position = new Position();
		position.setLatitude(info.getDouble("latitude"));
		position.setLongitude(info.getDouble("longitude"));
		position.setAltitude(info.getDouble("altitude"));
		position.setNumberOfSatellites(info.getInt("numberOfSatellites"));
		position.setSpeed(info.getDouble("speedKnots"));
		try {
			Date date = new SimpleDateFormat("ddMMyyHHmmss.SSS").parse(info.getString("date") + info.getString("time"));
			position.setTime(date.getTime() / 1000);
		} catch (Exception e) {
		}
		return position;
	}

}
