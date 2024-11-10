package com.qp.assessment.gsms.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qp.assessment.gsms.response.MessageResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<MessageResponse> handleCustomException(CustomException ex, WebRequest request, Locale locale) {
		MessageResponse messageResponse = MessageResponse.error(ex.getMessage());
		log.error(messageResponse.getErrorMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<MessageResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Locale locale) {
		MessageResponse messageResponse = MessageResponse.error(ex.getLocalizedMessage(messageSource, locale));
		log.error(ex.getMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public final ResponseEntity<MessageResponse> handleUsernameNotFoundException(InternalAuthenticationServiceException ex, WebRequest request, Locale locale) {
		MessageResponse messageResponse = MessageResponse.error(messageSource.getMessage("username.not.found", null, locale));
		log.error(ex.getMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errors = "";
		List<ObjectError> objErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError error : objErrors) {
			if(errors.equals("")) {
				errors += " - " + error.getDefaultMessage() + "\n";
			}
			else {
				errors += error.getDefaultMessage() + "\n";
			}
		}
	    
	    MessageResponse error = MessageResponse.error(errors);
	    log.error(ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<MessageResponse> handleMethodArgumentNotValidException(Exception ex, WebRequest request, Locale locale) {
		MessageResponse messageResponse = MessageResponse.error(ex.getMessage());
		log.error(ex.getClass() + " - " + ex);
		ex.printStackTrace();
		return new ResponseEntity<>(messageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldValidationException.class)
	public final ResponseEntity<MessageResponse> handleFieldValidationException(FieldValidationException ex, WebRequest request, Locale locale) {
		MessageResponse messageResponse = MessageResponse.error(ex.getMessage());
		log.error(messageResponse.getErrorMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
	}
}
