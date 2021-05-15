package com.cowin.GetVaccine.converters;

import java.util.List;

import com.cowin.GetVaccine.dto.Center;
import com.cowin.GetVaccine.outputDTO.AvailableCenter;

public interface Converter {

	public AvailableCenter convertToAvailableCenters(Center center, List<String> availableDates);
}
