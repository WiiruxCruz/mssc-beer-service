package guru.springframework.mssc_beer_service.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.mssc_beer_service.web.model.BeerDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getBeerById() throws Exception {
		mockMvc.perform(
			get(
				"/api/v1/beer/" + UUID.randomUUID().toString()
			)
			.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		;
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(
			post("/api/v1/beer/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson)
		)
		.andExpect(status().isCreated())
		;
		
	}

	@Test
	void updateBeerById() throws Exception{
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(
			put("/api/v1/beer/" + UUID.randomUUID().toString())
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson)
		)
		.andExpect(status().isNoContent())
		;
	}

}
