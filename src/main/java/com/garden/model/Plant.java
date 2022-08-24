package com.garden.model;

public class Plant {
	private long id;
	private String name;
	private int temp_needed;
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