package com.example.hpc.utils.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler( com.example.hpc.utils.exceptions.ExceptionHandler.class)
	public ResponseEntity<?> baseExceptionHandler( com.example.hpc.utils.exceptions.ExceptionHandler exceptionHandler) {
		return ResponseEntity.status(exceptionHandler.getCode()).body(exceptionHandler.getMessage());
	}
}
