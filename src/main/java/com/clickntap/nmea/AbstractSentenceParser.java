package com.clickntap.nmea;

abstract class AbstractSentenceParser implements SentenceParser {
	private String name;

	public AbstractSentenceParser() throws Exception {
		this.name = "$" + this.getClass().getSimpleName().substring(0, 5);
		if (!name.startsWith("$GP")) {
			throw new Exception("bad class name");
		}
	}

	public String getCode() {
		return getName().substring(3);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean valid(String line) throws Exception {
		return line.startsWith(name);
	}

}
