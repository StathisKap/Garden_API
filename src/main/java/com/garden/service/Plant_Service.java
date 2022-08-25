package com.garden.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garden.exception.PlantNotFound;
import com.garden.model.Plant;
import com.garden.repo.Plant_Repo;

@Service
public class Plant_Service {
	@Autowired
	Plant_Repo plant_Repo;
	
	public List<Plant> get_all_plants() {
		List<Plant> plants = new ArrayList<Plant>();
		plant_Repo.findAll().forEach(plant -> plants.add(plant));
		return plants;
	}

	// getting a specific record by using the method findById() of CrudRepository
	public Plant get_plant_by_Id(int id) {
		return plant_Repo.findById(id).orElseThrow(PlantNotFound::new);
	}

	public Plant add_plant(Plant plant) {
		return plant_Repo.save(plant);
	}

	public void remove_plant (int id) {
		plant_Repo.deleteById(id);
	}

	public void update_plant(Plant plant) {
		plant_Repo.save(plant);
	}
}