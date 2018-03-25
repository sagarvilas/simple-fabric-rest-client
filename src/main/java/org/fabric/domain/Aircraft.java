package org.fabric.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {

	private String aircraftId;
	private String ownershipType;
	private String firstClassSeats;
	private String businessClassSeats;
	private String economyClassSeats;

	public String getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(String aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}

	public String getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(String firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	public String getBusinessClassSeats() {
		return businessClassSeats;
	}

	public void setBusinessClassSeats(String businseeClssSeats) {
		this.businessClassSeats = businseeClssSeats;
	}

	public String getEconomyClassSeats() {
		return economyClassSeats;
	}

	public void setEconomyClassSeats(String economyClassSeats) {
		this.economyClassSeats = economyClassSeats;
	}
	
	@Override
	public String toString() {
		return "aircraftId: "+aircraftId+", ownershipType: "+ownershipType+
				",\n firstClassSeats: "+firstClassSeats+
				",\n businessClassSeats: "+businessClassSeats+
				",\n economyClassSeats: "+economyClassSeats;
	}
}
