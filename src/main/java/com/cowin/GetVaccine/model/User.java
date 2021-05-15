package com.cowin.GetVaccine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="dob")
	private Date dob;
	@Column(name="active")
	private boolean active;
	@Column(name="pincode")
	private String pincode;
	@Column(name="district_id")
	private String districtID;
	@Column(name="notification_sent_timings")
	private Date notificationSentTimings;
	@Column(name="message_id")
	private String messageID;

	public User() {
	}

	public User(String firstName, String lastName, Date dob, boolean active, Date notificationSentTimings,
			String messageID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.active = active;
		this.notificationSentTimings = notificationSentTimings;
		this.messageID = messageID;
	}

	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public Date getNotificationSentTimings() {
		return notificationSentTimings;
	}

	public void setNotificationSentTimings(Date notificationSentTimings) {
		this.notificationSentTimings = notificationSentTimings;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

}
