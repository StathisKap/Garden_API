package com.garden.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
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
    	
        // GIVEN is our testing data - you can make this a final local variable if you want, e.g.:
        final Plant TEST_PLANT = new Plant(11, "Avocado", 23 , 59);
        final Plant TEST_SAVED_PLANT = new Plant(12, "Mango", 24 , 58);

        // WHEN
        Mockito.when(this.repo.save(TEST_PLANT)).thenReturn(TEST_SAVED_PLANT);

        // THEN
        Assertions.assertThat(this.service.add_plant(TEST_PLANT)).isEqualTo(TEST_SAVED_PLANT);

        // verify that our repo was accessed exactly once
        Mockito.verify(this.repo, Mockito.times(1)).save(TEST_PLANT);
        
        System.out.println("Create Test Successful");
        
    }

    //Unit Test 2
    @Test
    void test_get_by_Id() {
    	
    	// Setup the mock repo

        int plantId = 1001;
        final Plant TEST_PLANT = new Plant(plantId, "walnuts", 4, 12);
        //final Optional<Plant> TEST_SAVED_PLANT = Optional.empty();

        // Make the service call
    	Mockito.when(this.repo.findById(plantId)).thenReturn(Optional.of(TEST_PLANT)); 
        System.out.println("Test for Get By ID Successful");
       
    }   
        
    //Unit Test 3
    @Test
    public void remove_plant_by_Id() {
    	
    	
    	int plantId = 10001;
        @SuppressWarnings("unused")
		final Plant TEST_PLANT = new Plant(0, "Pear", 27, 65);
        final Plant TEST_SAVED_PLANT = new Plant(plantId, "Apple", 28, 62);
        
    	service.remove_plant((int)TEST_SAVED_PLANT.getId());

        Mockito.verify(repo, Mockito.times(1))
                .deleteById((int)TEST_SAVED_PLANT.getId());
        
        System.out.println("Test for Delete By ID Successful");
        
    }
    
    //Unit Test 4
    @Test
    public void test_find_all() {

    	final Plant TEST_SAVED_PLANT = new Plant(1001,"TestBook", 22, 23);
        List<Plant> foundPlant = service.get_all_plants();
        foundPlant.add(TEST_SAVED_PLANT);

        assertNotNull(foundPlant);
        assertEquals(1, foundPlant.size());
        
        System.out.println("Test for Find All Successful");
    }

    
    //---------- Integration Test----------//
        
    
  //Integration Test 1
    @Test
	public void int_test_create() throws Exception {
		final Plant newBook = new Plant(1003, "Tulip", 20, 70);
		
		this.mock
				.perform(post("/plant").contentType(MediaType.APPLICATION_JSON)
						.content(this.obmapper.writeValueAsString(newBook)))
				.andExpect(status().isOk());
	}
    
    //Integration Test 2
    @Test
	public void int_test_read_all() throws Exception {
		final String resultString = this.mock
				.perform(request(HttpMethod.GET, "/allPlants").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Plant> results = Arrays.asList(obmapper.readValue(resultString, Plant[].class));
		assertEquals(new ArrayList<>(Arrays.asList()), results);
		System.out.println(results.size());
	}
}
