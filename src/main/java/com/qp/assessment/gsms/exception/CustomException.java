package com.qp.assessment.gsms.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class CustomException extends Exception{
	private static final long serialVersionUID = 1690756533882619346L;

	private Object[] args;

	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Object...args) {
		super(message);
		this.args = args;
	}

	public String getLocalizedMessage(MessageSource messageSource, Locale locale) {
		String errorMessage = messageSource.getMessage(this.getMessage(), this.args, locale);
		if(errorMessage != null) {
			return errorMessage;
		}
		
		return super.getLocalizedMessage();
	}

}
