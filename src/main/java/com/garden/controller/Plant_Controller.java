package com.garden.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garden.model.Plant;
import com.garden.service.Plant_Service;

//mark class as Controller
@RestController
public class Plant_Controller {

	//auto-wire the PlantsService class
	@Autowired
	Plant_Service Plant_Service;

	@GetMapping("/")
	public String greeting() {
		return "Welcome Student. Please go to \"/allPlants\" for all available Plants";
	}

	//creating a get mapping that retrieves all the Plant detail from the database
	@GetMapping("/allPlants")
	private List<Plant> getAllPlants() {
		return 	Plant_Service.get_all_plants();
	}
	//creating a get mapping that retrieves the detail of a specific plant
	@GetMapping("/plant/{id}")
	private Plant getPlants(@PathVariable("id") int id){
		return 	Plant_Service.get_plant_by_Id(id);
	}

	//creating a delete mapping that deletes a specified plant
	@DeleteMapping("/plant/{id}")
	private void deleteBook(@PathVariable("id") int id){
		Plant_Service.remove_plant(id);
	}

	//creating post mapping that post the plant detail in the database
	@PostMapping("/plant")
	private long saveBook(@RequestBody Plant plant){
		Plant_Service.add_plant(plant);
		return plant.getId();
	}

	//creating put mapping that updates the plant detail
	@PutMapping("/plant")
	private Plant update(@RequestBody Plant plant){
		Plant_Service.update_plant(plant);
		return plant;
	}
}
