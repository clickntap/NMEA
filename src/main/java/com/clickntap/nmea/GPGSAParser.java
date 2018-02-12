package com.clickntap.nmea;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class GPGSAParser extends AbstractSentenceParser {

	public GPGSAParser() throws Exception {
		super();
	}

	public JSONObject parse(String line) throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", getCode());
		String[] tokens = StringUtils.split(line, ',');
		json.put("automatic", tokens[1]);
		json.put("mode", tokens[2]);
		return json;
	}

}
