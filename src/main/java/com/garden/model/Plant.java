package com.garden.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//mark class as an Entity   
@Table  
@AllArgsConstructor
@NoArgsConstructor
@Entity  
public class Plant {
	public Plant() {
		super();
	}

	public Plant(long id, String name, int temp_needed, int humidity_needed) {
		super();
		this.id = id;
		this.name = name;
		this.temp_needed = temp_needed;
		this.humidity_needed = humidity_needed;
	}

	@Id
	@Column
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@Column
	private int temp_needed;
	@Column
	private int humidity_needed;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTemp_needed() {
		return temp_needed;
	}

	public void setTemp_needed(int temp_needed) {
		this.temp_needed = temp_needed;
	}

	public int getHumidity_needed() {
		return humidity_needed;
	}

	public void setHumidity_needed(int humidity_needed) {
		this.humidity_needed = humidity_needed;
	}
}