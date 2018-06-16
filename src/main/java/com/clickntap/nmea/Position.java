package com.clickntap.nmea;

public class Position {
	private Long time;
	private Double altitude;
	private Double longitude;
	private Double latitude;
	private Integer numberOfSatellites;
	private Double speed;
	private Double trackMadeGood;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getNumberOfSatellites() {
		return numberOfSatellites;
	}

	public void setNumberOfSatellites(Integer numberOfSatellites) {
		this.numberOfSatellites = numberOfSatellites;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getTrackMadeGood() {
		return trackMadeGood;
	}

	public void setTrackMadeGood(Double trackMadeGood) {
		this.trackMadeGood = trackMadeGood;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getTime());
		sb.append(',');
		sb.append(getLatitude());
		sb.append(',');
		sb.append(getLongitude());
		sb.append(',');
		sb.append(getAltitude());
		sb.append(',');
		sb.append(getSpeed());
		sb.append(',');
		sb.append(getTrackMadeGood());
		sb.append(',');
		sb.append(getNumberOfSatellites());
		return sb.toString();
	}

}
