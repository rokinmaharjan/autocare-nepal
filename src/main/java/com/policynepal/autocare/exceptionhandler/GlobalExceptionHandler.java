package com.policynepal.autocare.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<Object> handleIOException(final GlobalException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				new ErrorResponse(ex.getStatus().value(), ex.getStatus().getReasonPhrase(), ex.getMessage()),
				new HttpHeaders(), ex.getStatus(), request);
	}
}
