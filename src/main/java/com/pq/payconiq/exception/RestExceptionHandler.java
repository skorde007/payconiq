package com.pq.payconiq.exception;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", errors);
		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleNullPointer(NullPointerException ex) {
		List<String> errors = Arrays.asList("Data Not present" + ": " + ex.getLocalizedMessage());

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", errors);
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getConstraintViolations().stream().map(x -> x.getMessage()));
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StockFoundException.class)
	protected ResponseEntity<Object> handleStockFoundException(StockFoundException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
}
