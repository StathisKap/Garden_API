package com.garden.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;  

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Garden {

	@Id
	@Column
	private int Gardenid;
	@Column
	private List<Plant> plants;
	@Column
	private Air air;
	@Column
	private Soil soil;

	public int getGardenid() {
		return Gardenid;
	}
	public void setGardenid(int gardenid) {
		Gardenid = gardenid;
	}
	public List<Plant> getPlants() {
		return plants;
	}
	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}
	public Air getAir() {
		return air;
	}
	public void setAir(Air air) {
		this.air = air;
	}
	public Soil getSoil() {
		return soil;
	}
	public void setSoil(Soil soil) {
		this.soil = soil;
	}
}