package com.java.exception;

import com.java.model.ResponseMessage;

public class MyExceptionHandler extends Throwable {

	private String ErrorMessage = "";
	private String ExceptionName = "";
	private String MethodName = "";

	public MyExceptionHandler(String ExceptionName, String MethodName) {
		this.ExceptionName = ExceptionName;
		this.MethodName = MethodName;

		ExceptionHandler();
		errMessage();
	}

	private void ExceptionHandler() {
		if (ExceptionName.equals("IOException")) {
			if (MethodName.equals("uploadFile")) {
				ErrorMessage = "Unable to upload file. Please try again later.";
			}
		}
	}

	private ResponseMessage errMessage() {
		ResponseMessage response = new ResponseMessage();
		response.setStatusCode(111);
		// response.setErrorMessage(ExceptionName+" in "+MethodName);
		return response;

	}
}
