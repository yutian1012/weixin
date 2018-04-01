package org.ipph.app.weixin.model.patent;

import java.util.List;

public class PatentResponseModel {
	private int from;
	private String message;
	private List<PatentModel> results;
	private int status;
	private int to;
	private int total;
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<PatentModel> getResults() {
		return results;
	}
	public void setResult(List<PatentModel> results) {
		this.results = results;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
