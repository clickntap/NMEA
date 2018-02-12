package com.clickntap.nmea;

import org.json.JSONObject;

interface SentenceParser {
	public String getCode() throws Exception;

	public boolean valid(String line) throws Exception;

	public JSONObject parse(String line) throws Exception;
}