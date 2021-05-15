package com.cowin.GetVaccine.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cowin.GetVaccine.outputDTO.AvailableCenter;
import com.cowin.GetVaccine.service.AWSAPI;
import com.cowin.GetVaccine.service.GetVaccineAvailabilityService;

@Component
public class VaccineNotificationScheduler {
	
	private static String COUNTRY_CODE = "+91";

	@Resource(name = "getVaccineAvailabilityService")
	private GetVaccineAvailabilityService getVaccineAvailabilityService;
	@Resource(name = "awsAPI")
	private AWSAPI awsAPI;

	/**
	 * This method is used to identify vaccine availability based on data entered by
	 * user and send AWS notification.
	 */
	@Scheduled(fixedDelay = 60000, initialDelay = 3000)
	public void identifyVaccineAvailability() {
		getVaccineAvailabilityService.findEligibleUsers().forEach(eu -> {
			List<AvailableCenter> availableCenters = new ArrayList<AvailableCenter>();
			availableCenters.addAll(
					getVaccineAvailabilityService.findVaccineAvailabilityByPinCode(eu.getPincode(), eu.getDob()));
			availableCenters.addAll(
					getVaccineAvailabilityService.findVaccineAvailabilityByDistrictID(eu.getDistrictID(), eu.getDob()));

			if (!availableCenters.isEmpty()) {
				String mobileNumber = COUNTRY_CODE + eu.getMobileNumber();
				String messageID = awsAPI.sendSMSMessage(prepareSMS(availableCenters), mobileNumber);
				getVaccineAvailabilityService.updateUserTable(eu, messageID);
			}
		});

	}

	private String prepareSMS(List<AvailableCenter> availableCenters) {
		StringBuilder msg = new StringBuilder();
		availableCenters.forEach(ac -> {
			msg.append(ac.getName()).append(" - ").append(String.join(",", ac.getAvailableDates())).append(" # ");
		});
		
		return msg.toString();

	}

	private Date convertStringToDate(String dateInString) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			return formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
