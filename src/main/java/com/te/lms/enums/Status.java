package com.te.lms.enums;

public enum Status {
	TOBESTARTED("TOBESTARTED"),INPROGRESS("INPROGRESS"),COMPLETED("COMPLETED");
	
	private final String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
