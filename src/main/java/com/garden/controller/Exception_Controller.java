package com.garden.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.garden.exception.PlantNotFound;


public class Exception_Controller {
	 @ExceptionHandler(value = PlantNotFound.class)
	   public ResponseEntity<Object> exception(PlantNotFound exception) {
	      return new ResponseEntity<>("Plant Not Found. Please enter the right ID", HttpStatus.NOT_FOUND);
	   }
}