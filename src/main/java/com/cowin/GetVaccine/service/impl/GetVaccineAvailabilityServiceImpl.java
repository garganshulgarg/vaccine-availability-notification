package com.cowin.GetVaccine.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.cowin.GetVaccine.Repository.UserRepository;
import com.cowin.GetVaccine.converters.Converter;
import com.cowin.GetVaccine.dto.Center;
import com.cowin.GetVaccine.dto.Centers;
import com.cowin.GetVaccine.dto.Session;
import com.cowin.GetVaccine.model.User;
import com.cowin.GetVaccine.outputDTO.AvailableCenter;
import com.cowin.GetVaccine.service.CowinAPI;
import com.cowin.GetVaccine.service.GetVaccineAvailabilityService;

public class GetVaccineAvailabilityServiceImpl implements GetVaccineAvailabilityService {

	private Logger logger = LogManager.getLogger(GetVaccineAvailabilityServiceImpl.class);

	private CowinAPI cowinAPI;
	private Converter converter;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findEligibleUsers() {
		Predicate<User> isUserNotNotified = u -> null == u.getNotificationSentTimings() || u.getNotificationSentTimings().before(Date.from(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()).toInstant()));
		List<User> activeUsers = userRepository.findAllTheActiveAndEligibleUsers();
		List<User> eligibleUsers = activeUsers.stream()
					.filter(isUserNotNotified)
					.collect(Collectors.toList());
		
		return eligibleUsers;
	}
	
	@Override
	public void updateUserTable(User user, String messageId) {
		user.setNotificationSentTimings(new Date());
		user.setMessageID(messageId);
		userRepository.save(user);
	}

	@Override
	public List<AvailableCenter> findVaccineAvailabilityByPinCode(String pinCode, Date dob) {
		Centers centers = cowinAPI.findApplicableCentersByPinCode(pinCode);
		if (null != centers && null != centers.getCenters() && centers.getCenters().size() > 0) {			
			return filterBasedOnAvailability(centers.getCenters(), ageCalculation(dob));
		}
		
		return null;
	}
	
	@Override
	public List<AvailableCenter> findVaccineAvailabilityByDistrictID(String districtID, Date dob) {
		Centers centers = cowinAPI.findApplicableCentersByDistrictID(districtID);
		if (null != centers && null != centers.getCenters() && centers.getCenters().size() > 0) {			
			return filterBasedOnAvailability(centers.getCenters(), ageCalculation(dob));
		}
		
		return null;
	}

	private List<AvailableCenter> filterBasedOnAvailability(List<Center> centers, int age) {
		Predicate<Session> isSessionAvailable = s -> s.getAvailableCapacity() > 0;
		Predicate<Session> isAgeCriteriaMet = s -> age >= s.getMinAgeLimit();
		List<AvailableCenter> availableCenterList = new ArrayList<AvailableCenter>();
		centers.forEach(center -> {
			List<String> availableDates = center.getSessions().stream()
												.filter(isAgeCriteriaMet)
												.filter(isSessionAvailable)
												.map(s -> s.getDate())
												.collect(Collectors.toList());
			if(null != availableDates && availableDates.size() > 0) {
				availableCenterList.add(converter.convertToAvailableCenters(center, availableDates));				
			}
		});
		
		return availableCenterList;
	}
	
	
	
	private int ageCalculation(Date dob) {
		LocalDate today = LocalDate.now();                 
		LocalDate birthday = dob.toInstant()
								.atZone(ZoneId.systemDefault())
								.toLocalDate();
		return Period.between(birthday, today).getYears();
		 
	}

	public void setCowinAPI(CowinAPI cowinAPI) {
		this.cowinAPI = cowinAPI;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}

	

	

	

	
	

}
