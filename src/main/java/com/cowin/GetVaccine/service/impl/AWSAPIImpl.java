package com.cowin.GetVaccine.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.cowin.GetVaccine.service.AWSAPI;

public class AWSAPIImpl implements AWSAPI{

	private AmazonSNSClient amazonSNSClient;
	@Override
	public String sendSMSMessage(String message, String phoneNumber) {
		PublishResult result = amazonSNSClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(updateAmazonSNSClient()));
		return result.getMessageId();
		
	}
	
	
	private  Map<String, MessageAttributeValue> updateAmazonSNSClient() {
		Map<String, MessageAttributeValue> smsAttributes =
		        new HashMap<String, MessageAttributeValue>();
		smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
		        .withStringValue("0.50") //Sets the max price to 0.50 USD.
		        .withDataType("Number"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		        .withStringValue("Promotional") //Sets the type to promotional.
		        .withDataType("String"));
		return smsAttributes;
	}
	public void setAmazonSNSClient(AmazonSNSClient amazonSNSClient) {
		this.amazonSNSClient = amazonSNSClient;
	}
	
	

}
