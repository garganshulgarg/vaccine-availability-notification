package com.cowin.GetVaccine.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cowin.GetVaccine.dto.Centers;
import com.cowin.GetVaccine.service.CowinAPI;

public class CowinAPIImpl implements CowinAPI {

	Logger logger = LogManager.getLogger(CowinAPIImpl.class);

	private RestTemplate restTemplate;

	@Override
	public Centers findApplicableCentersByPinCode(String pinCode) {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin")
				.queryParam("pincode", pinCode).queryParam("date", formatDate(new Date()));
		return makeAPICall(pinCode, builder);

	}

	@Override
	public Centers findApplicableCentersByDistrictID(String districtID) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict")
				.queryParam("district_id", districtID).queryParam("date", formatDate(new Date()));
		return makeAPICall(districtID, builder);
	}

	private Centers makeAPICall(String code, UriComponentsBuilder builder) {
		try {
			ResponseEntity<Centers> centers = updateRestTemplateConfig(restTemplate).exchange(builder.toUriString(),
					HttpMethod.GET, createRequestEntity(), Centers.class);
			if (null == centers.getBody()) {
				logger.log(Level.ERROR, "Unable to fetch centers for pinCode : " + code);
				return null;
			}
			return centers.getBody();

		} catch (RestClientException exception) {
			logger.log(Level.ERROR, exception);
		}

		return null;
	}

	private String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}

	private HttpEntity<String> createRequestEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		return entity;
	}

	private RestTemplate updateRestTemplateConfig(final RestTemplate restTemplate) {
		final SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = (SimpleClientHttpRequestFactory) restTemplate
				.getRequestFactory();
		simpleClientHttpRequestFactory.setReadTimeout(3000);
		simpleClientHttpRequestFactory.setConnectTimeout(3000);
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
