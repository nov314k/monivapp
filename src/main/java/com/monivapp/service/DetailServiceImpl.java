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
	private String restUrl;
			
	@Autowired
	public DetailServiceImpl(RestTemplate theRestTemplate, 
										@Value("${rest.url}") String theUrl) {
		restTemplate = theRestTemplate;
		restUrl = theUrl;
	}

	@Override
	public Detail getDetail(String title) {
		Detail theDetail = 
				restTemplate.getForObject(restUrl + title, Detail.class);
		return theDetail;
	}
}