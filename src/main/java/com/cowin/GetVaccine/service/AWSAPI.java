package com.cowin.GetVaccine.service;

import java.util.Map;

import com.amazonaws.services.sns.model.MessageAttributeValue;

public interface AWSAPI {

	public String sendSMSMessage(String message, 
			String phoneNumber);
}
