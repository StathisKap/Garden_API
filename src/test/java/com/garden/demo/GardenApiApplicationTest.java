package com.garden.demo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garden.model.Plant;
import com.garden.repo.Plant_Repo;
import com.garden.service.Plant_Service;


@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@ContextConfiguration //(classes ={GardenApiApplication.class, Plant.class, Plant_Service.class, Plant_Controller.class})
public class GardenApiApplicationTest {

    @Autowired
    private Plant_Service service;

    @MockBean
    private Plant_Repo repo;

    @Autowired
    private MockMvc mock;

    @SuppressWarnings("unused")
	private final Plant TEST_PLANT = new Plant(10,"Water Melon", 28, 60);


    @Autowired
	private ObjectMapper obmapper;


    //Unit Test Create 1
    @Test
    void test_create(){
    	int plantid = 11;

        // GIVEN is our testing data - you can make this a final local variable if you want, e.g.:
        final Plant TEST_PLANT = new Plant(plantid, "Avocado", 23 , 59);

        // WHEN
        Mockito.when(this.repo.save(TEST_PLANT)).thenReturn(TEST_PLANT);

        // THEN
        Assertions.assertThat(this.service.add_plant(TEST_PLANT)).isEqualTo(TEST_PLANT);

        // verify that our repo was accessed exactly once
        Mockito.verify(this.repo, Mockito.times(1)).save(TEST_PLANT);

        System.out.println("Create Test Successful");

    }


    //Unit Test Create 2
    @Test
    void test_update(){

        // GIVEN is our testing data - you can make this a final local variable if you want, e.g.:
        final Plant TEST_PLANT = new Plant(11, "Avocado", 23 , 59);

        // WHEN
        Mockito.when(this.repo.save(TEST_PLANT)).thenReturn(TEST_PLANT);

        // THEN
        Assertions.assertThat(this.service.update_plant(TEST_PLANT)).isEqualTo(TEST_PLANT);

        // verify that our repo was accessed exactly once
        Mockito.verify(this.repo, Mockito.times(1)).save(TEST_PLANT);

        System.out.println("Create Test Successful");

    }

    //Unit Test 3
    @Test
    void test_get_by_Id() {

        int plantId = 1001;
        final Plant TEST_PLANT = new Plant(plantId, "walnuts", 4, 12);

        // Make the service call
    	Mockito.when(this.repo.findById(plantId)).thenReturn(Optional.of(TEST_PLANT));
    	assertEquals(TEST_PLANT, service.get_plant_by_Id(plantId));
        System.out.println("Test for Get By ID Successful");
    }

    //Unit Test 4
    @Test
    public void remove_plant_by_Id() {
    	int plantId = 10001;
		final Plant TEST_PLANT = new Plant(plantId, "Pear", 27, 65);

    	service.remove_plant(plantId);

        Mockito.verify(repo, Mockito.times(1))
                .deleteById(TEST_PLANT.getId());

        System.out.println("Test for Delete By ID Successful");

    }

    //Unit Test 5
    @Test
    public void test_find_all() {

    	final Plant TEST_PLANT1 = new Plant(1001,"TestPlant1", 22, 23);
    	final Plant TEST_PLANT2 = new Plant(1002,"TestPlant2", 22, 23);
        List<Plant> foundPlant = service.get_all_plants();
        foundPlant.add(TEST_PLANT1);
        foundPlant.add(TEST_PLANT2);

        assertNotNull(foundPlant);
        assertEquals(2, foundPlant.size());

        System.out.println("Test for Find All Successful");
    }


  //---------- Integration Test----------//

  //Integration Test 1
    @Test
	public void test_homepage() throws Exception {
    	this.mock
				.perform(request(HttpMethod.GET, "/")
				.accept(MediaType.TEXT_EVENT_STREAM_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

    //Integration Test 2
    @Test
	public void test_all_plants() throws Exception {
		final String resultString = this.mock
				.perform(request(HttpMethod.GET, "/allPlants").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Plant> results = Arrays.asList(obmapper.readValue(resultString, Plant[].class));
		assertEquals(new ArrayList<>(Arrays.asList()), results);
		System.out.println(results.size());
	}

    //Integration Test 3
    @Test
	public void test_plant_by_id() throws Exception {
    	long plantid = 11;
		final Plant newPlant = new Plant((int) plantid, "Tulip", 20, 70);
		
		Mockito.when(this.repo.findById((int) plantid)).thenReturn(Optional.of(newPlant));

		 this.mock
				.perform(request(HttpMethod.GET, "/plant/{id}", Long.toString(plantid))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));

		System.out.println("Retured By ID");
	}


    //Integration Test 4
    @Test
	public void test_remove_plant_by_id() throws Exception {

    	int plantid = 2;
		final Plant newPlant = new Plant( plantid, "Tulip", 20, 70);

			this.mock
				.perform(request(HttpMethod.DELETE , "/plant/{id}", Long.toString(plantid)))
				.andExpect(status().isOk());

		System.out.println("Removed Plant By ID");
	}
    
  //Integration Test 5
    @Test
	public void int_test_create_new_plant() throws Exception {
    	long plantid = 1003;
		final Plant newPlant = new Plant((int) plantid, "Tulip", 20, 70);

		this.mock
				.perform(post("/plant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.obmapper.writeValueAsString(newPlant)))
				.andExpect(status().isOk()).andReturn().equals(newPlant.getId());
	}

    //Integration Test 6
    @Test
	public void int_test_update() throws Exception {
    	long plantid = 1003;
		final Plant newPlant = new Plant((int) plantid, "Tulip", 20, 70);

		this.mock
				.perform(request(HttpMethod.PUT, ("/plant"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.obmapper.writeValueAsString(newPlant)))
				.andExpect(status().isOk()).andReturn().equals(newPlant.getId());
	}
}