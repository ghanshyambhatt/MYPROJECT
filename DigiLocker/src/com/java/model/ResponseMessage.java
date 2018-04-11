package com.java.model;

public class ResponseMessage {
	public static int statusCode;
	public static String Message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		ResponseMessage.statusCode = statusCode;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		ResponseMessage.Message = Message;
	}
}
