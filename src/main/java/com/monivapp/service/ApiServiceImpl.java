package com.monivapp.service;

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
		
		// TODO Extract "&t=" and &type=movie into the properties file
		Detail theDetail = restTemplate.getForObject(
				apiUrl + "&t=" + title + "&type=movie", Detail.class);
		return theDetail;
	}
	
	@Override
	public Detail getPreview(String imdbId) {
		
		// TODO Extract "&t=" and &type=movie into the properties file
		Detail theDetail = restTemplate.getForObject(
				apiUrl + "&i=" + imdbId + "&type=movie", Detail.class);
		return theDetail;
	}
	
	@Override
	public SearchResult getSearchResult(String searchTerm) {

		// TODO Extract "&s=" and &type=movie into the properties file
		// TODO Enable other pages
		ResponseEntity<SearchResult> responseEntity =
				restTemplate.exchange(apiUrl + "&s=" + searchTerm + "&type=movie&page=1",
						HttpMethod.GET, null,
						new ParameterizedTypeReference<SearchResult>() {});
				SearchResult theSearchResult = responseEntity.getBody();
				return theSearchResult;
	}
}