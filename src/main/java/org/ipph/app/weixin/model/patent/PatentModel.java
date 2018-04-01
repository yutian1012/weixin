package org.ipph.app.weixin.model.patent;

import java.util.Date;

import org.ipph.app.weixin.gson.CustomDateTypeAdapter;

import com.google.gson.annotations.JsonAdapter;

public class PatentModel {
	private String appNumber;
	@JsonAdapter(CustomDateTypeAdapter.class)
	private Date appDate;
	private String applicant;
	public String getAppNumber() {
		return appNumber;
	}
	public void setAppNumber(String appNumber) {
		this.appNumber = appNumber;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	@Override
	public String toString() {
		return "PatentModel [appNumber=" + appNumber + ", appDate=" + appDate + ", applicant=" + applicant + "]";
	}
	
}
