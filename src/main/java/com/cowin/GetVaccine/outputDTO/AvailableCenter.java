package com.cowin.GetVaccine.outputDTO;

import java.util.List;

public class AvailableCenter {

	private Integer centerId;
	private String name;
	private String address;
	private String stateName;
	private String districtName;
	private String blockName;
	private Integer pincode;
	private List<String> availableDates;
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public List<String> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(List<String> availableDates) {
		this.availableDates = availableDates;
	}
	
	
	
}
