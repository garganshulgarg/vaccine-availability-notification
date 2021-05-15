package com.cowin.GetVaccine.service;

import com.cowin.GetVaccine.dto.Centers;

public interface CowinAPI {

	public Centers findApplicableCentersByPinCode(String pinCode);
	public Centers findApplicableCentersByDistrictID(String districtID);
}
