package com.clickntap.nmea;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class NMEA {

	private List<SentenceParser> parsers;

	public NMEA() throws Exception {
		parsers = new ArrayList<SentenceParser>();
		parsers.add(new GPGGAParser());
		parsers.add(new GPGSVParser());
		parsers.add(new GPRMCParser());
		parsers.add(new GPGSAParser());
		parsers.add(new GPVTGParser());
	}

	public JSONObject parse(String text) throws Exception {
		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();
		List<JSONObject> items = new ArrayList<JSONObject>();
		List<String> unknowns = new ArrayList<String>();
		List<String> messages = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (Character.isLetterOrDigit(c) || c == '*' || c == ',' || c == '$' || c == '.') {
				sb.append(c);
			}
		}
		text = sb.toString();
		String[] lines = StringUtils.split(text, '$');
		for (String line : lines) {
			if (!line.isEmpty()) {
				line = '$' + line;
				messages.add(line);
				boolean ok = false;
				for (SentenceParser parser : parsers) {
					if (parser.valid(line)) {
						try {
							JSONObject item = parser.parse(line);
							items.add(item);
							if (parser.getCode().equals("GGA")) {
								info.put("latitude", item.get("latitude"));
								info.put("longitude", item.get("longitude"));
								info.put("altitude", item.get("altitude"));
								info.put("numberOfSatellites", item.get("numberOfSatellites"));
							}
							if (parser.getCode().equals("VTG")) {
								info.put("speedKnots", item.get("speedKnots"));
								info.put("speedKmPerHours", item.get("speedKmPerHours"));
								info.put("trackMadeGood", item.get("trackMadeGood"));
							}
							if (parser.getCode().equals("RMC")) {
								info.put("date", item.get("date"));
								info.put("time", item.get("time"));
							}
						} catch (Exception e) {
						}
						ok = true;
					}
				}
				if (!ok) {
					unknowns.add(line);
				}
			}
		}
		json.put("items", items);
		if (unknowns.size() > 0) {
			json.put("unknowns", unknowns);
		}
		json.put("messages", messages);
		json.put("info", info);
		return json;
	}

}
