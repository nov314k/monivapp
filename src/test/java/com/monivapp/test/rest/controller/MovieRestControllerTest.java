package com.monivapp.test.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.monivapp.entity.Movie;
import com.monivapp.rest.MovieRestController;
import com.monivapp.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieRestController.class)
public class MovieRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieService;

	Movie mockMovie = new Movie("Test Movie", 0);

	String testMovie = "{\"title\":\"Test Movie\",\"votes\":\"0\"}";

	@Test
	public void retrieveDetailsForMovie() throws Exception {

		Mockito.when(movieService.getMovie(Mockito.anyInt())).thenReturn(mockMovie);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/movies").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{title:Test course,votes:0}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}