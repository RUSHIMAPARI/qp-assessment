package com.qp.assessment.gsms.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class FieldValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private Object[] args;

	public FieldValidationException(String message) {
		super(message);
	}
	
	public FieldValidationException(String message, Object...args) {
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
