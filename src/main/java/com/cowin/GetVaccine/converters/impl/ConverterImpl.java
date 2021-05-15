package com.cowin.GetVaccine.converters.impl;

import java.util.List;

import com.cowin.GetVaccine.converters.Converter;
import com.cowin.GetVaccine.dto.Center;
import com.cowin.GetVaccine.outputDTO.AvailableCenter;

public class ConverterImpl implements Converter{

	@Override
	public AvailableCenter convertToAvailableCenters(Center center, List<String> availableDates) {
		AvailableCenter availableCenter = new AvailableCenter();
		availableCenter.setCenterId(center.getCenterId());
		availableCenter.setName(center.getName());
		availableCenter.setAddress(center.getAddress());
		availableCenter.setBlockName(center.getBlockName());
		availableCenter.setDistrictName(center.getDistrictName());
		availableCenter.setPincode(center.getPincode());
		availableCenter.setStateName(center.getStateName());
		availableCenter.setAvailableDates(availableDates);
		return availableCenter;
	}

}
