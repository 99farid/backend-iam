package com.lawencon.assetsmanagement.helper;

public class EmailModel {

	private String to;
	
	private String subject;
	
	private String text;
	
	private byte[] fileToAttach;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getFileToAttach() {
		return fileToAttach;
	}

	public void setFileToAttach(byte[] fileToAttach) {
		this.fileToAttach = fileToAttach;
	}
}