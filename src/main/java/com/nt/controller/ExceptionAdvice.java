package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nt.exception.GuestNotFoundException;
import com.nt.exception.GuestServiceException;
import com.nt.model.GuestError;

@ControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(value=GuestServiceException.class)
	public ResponseEntity<GuestError> mapException(GuestServiceException gse){
		System.out.println("advice");
		GuestError error=new GuestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), gse.getMessage());
		return new ResponseEntity<GuestError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value=GuestNotFoundException.class)
	public ResponseEntity<GuestError> mappigException(GuestNotFoundException gse){
		System.out.println("advice");
		GuestError error=new GuestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), gse.getMessage());
		return new ResponseEntity<GuestError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
