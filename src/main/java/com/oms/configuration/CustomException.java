package com.oms.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomException {

	private HttpStatus httpStatus;
	private String message;
	private List<String> errors;

	public CustomException(HttpStatus httpStatus, String message, List<String> errors) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors = errors;
	}

	public CustomException(HttpStatus httpStatus, String message, String error) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		errors = Arrays.asList(error);
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	

}
