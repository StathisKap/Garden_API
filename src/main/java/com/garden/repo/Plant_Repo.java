package com.garden.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.garden.model.Plant;

@Repository
public interface Plant_Repo extends CrudRepository<Plant, Integer> {
}