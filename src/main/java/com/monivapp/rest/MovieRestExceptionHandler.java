package com.monivapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieRestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleException(
			MovieNotFoundException exc) {
		
		MovieErrorResponse error = new MovieErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleException(Exception exc) {
		
		MovieErrorResponse error = new MovieErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleException(
			MovieAddException exc) {
		
		MovieErrorResponse error = new MovieErrorResponse();
		error.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
	}
	
	// TODO Consider combining with handleException(MovieAddException exc) 
	@ExceptionHandler
	public ResponseEntity<MovieErrorResponse> handleException(
			MovieVoteQuotaException exc) {
		
		MovieErrorResponse error = new MovieErrorResponse();
		error.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
	}
}