package com.clickntap.nmea;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class GPGSVParser extends AbstractSentenceParser {

	public GPGSVParser() throws Exception {
		super();
	}

	public JSONObject parse(String line) throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", getCode());
		String[] tokens = StringUtils.split(line, ',');
		json.put("numberOfMessages", Integer.parseInt(tokens[1]));
		json.put("messageNumber", Integer.parseInt(tokens[2]));
		json.put("totalNumber", Integer.parseInt(tokens[3]));
		json.put("prnNumber", Integer.parseInt(tokens[4]));
		json.put("elevation", Integer.parseInt(tokens[5]));
		json.put("azimuth", Integer.parseInt(tokens[6]));
		return json;
	}

}
