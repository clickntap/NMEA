package com.clickntap.nmea;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class GPRMCParser extends AbstractSentenceParser {

	public GPRMCParser() throws Exception {
		super();
	}

	public JSONObject parse(String line) throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", getCode());
		String[] tokens = StringUtils.split(line, ',');
		json.put("time", tokens[1]);
		json.put("validity", tokens[2]);
		json.put("latitude", NMEAUtils.parseLatitude(tokens[3], tokens[4]));
		json.put("longitude", NMEAUtils.parseLongitude(tokens[5], tokens[6]));
		json.put("speedKnots", Double.parseDouble(tokens[7]));
		json.put("trackMadeGood", Double.parseDouble(tokens[8]));
		json.put("date", tokens[9]);
		return json;
	}

}
