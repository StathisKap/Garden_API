package com.garden.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Plant does not exist with that ID")
public class PlantNotFound extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
}