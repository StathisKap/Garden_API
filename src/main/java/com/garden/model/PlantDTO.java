package com.garden.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class PlantDTO {
	@Id
	@Column
	private int id;
	@Column
	private String name;
}