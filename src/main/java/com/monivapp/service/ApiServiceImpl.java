package com.monivapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monivapp.entity.Detail;
import com.monivapp.entity.SearchResult;

@Service
@PropertySource("classpath:application.properties")
public class ApiServiceImpl implements ApiService {

	private RestTemplate restTemplate;
	private String apiUrl;
			
	@Autowired
	public ApiServiceImpl(RestTemplate theRestTemplate,
			@Value("${omdb.api.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		apiUrl = theUrl;
	}

	@Override
	public Detail getDetail(String title) {
		
		// TODO Extract "&t=" into the properties file
		Detail theDetail = restTemplate.getForObject(apiUrl + "&t=" + title,
				Detail.class);
		return theDetail;
	}
	
	@Override
	public List<SearchResult> getSearchResults(String searchTerm) {

		// TODO Extract "&s=" into the properties file
		ResponseEntity<List<SearchResult>> responseEntity =
				restTemplate.exchange(apiUrl + "&s=" + searchTerm, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<SearchResult>>() {});
				List<SearchResult> searchResults = responseEntity.getBody();
				return searchResults;
	}
}