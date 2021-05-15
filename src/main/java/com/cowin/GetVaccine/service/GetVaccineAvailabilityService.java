package com.cowin.GetVaccine.service;

import java.util.Date;
import java.util.List;

import com.cowin.GetVaccine.model.User;
import com.cowin.GetVaccine.outputDTO.AvailableCenter;

public interface GetVaccineAvailabilityService {

	public List<User> findEligibleUsers();
	public void updateUserTable(User user, String messageId);
	public List<AvailableCenter> findVaccineAvailabilityByPinCode(String pinCode, Date dob);
	public List<AvailableCenter> findVaccineAvailabilityByDistrictID(String districtID, Date dob);
}
