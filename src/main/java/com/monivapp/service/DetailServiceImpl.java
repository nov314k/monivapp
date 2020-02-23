package com.monivapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monivapp.entity.Detail;

@Service
@PropertySource("classpath:application.properties")
public class DetailServiceImpl implements DetailService {

	private RestTemplate restTemplate;
	private String apiUrl;
			
	@Autowired
	public DetailServiceImpl(RestTemplate theRestTemplate,
			@Value("${omdb.api.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		apiUrl = theUrl;
	}

	@Override
	public Detail getDetail(String title) {
		
		Detail theDetail = restTemplate.getForObject(apiUrl + title, Detail.class);
		return theDetail;
	}
}