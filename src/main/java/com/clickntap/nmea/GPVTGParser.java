package com.clickntap.nmea;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class GPVTGParser extends AbstractSentenceParser {

	public GPVTGParser() throws Exception {
		super();
	}

	public JSONObject parse(String line) throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", getCode());
		String[] tokens = StringUtils.split(line, ',');
		json.put("trackMadeGood", Double.parseDouble(tokens[1]));
		json.put("speedKnots", Double.parseDouble(tokens[4]));
		json.put("speedKmPerHours", Double.parseDouble(tokens[6]));
		return json;
	}

}
