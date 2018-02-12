package com.clickntap.nmea;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class GPGGAParser extends AbstractSentenceParser {

	public GPGGAParser() throws Exception {
		super();
	}

	public JSONObject parse(String line) throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", getCode());
		String[] tokens = StringUtils.split(line, ',');
		json.put("time", tokens[1]);
		json.put("latitude", NMEAUtils.parseLatitude(tokens[2], tokens[3]));
		json.put("longitude", NMEAUtils.parseLongitude(tokens[4], tokens[5]));
		json.put("fixQuality", Integer.parseInt(tokens[6]));
		json.put("numberOfSatellites", Integer.parseInt(tokens[7]));
		json.put("horizontalDilutionOfPosition", Double.parseDouble(tokens[8]));
		json.put("altitude", Double.parseDouble(tokens[9]));
		json.put("altitudeMetric", tokens[10]);
		json.put("geoidal", Double.parseDouble(tokens[11]));
		json.put("geoidalMetric", tokens[12]);
		return json;
	}

}
