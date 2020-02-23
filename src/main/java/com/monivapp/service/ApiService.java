package com.monivapp.service;

import java.util.List;

import com.monivapp.entity.Detail;
import com.monivapp.entity.SearchResult;

public interface ApiService {

	public Detail getDetail(String title);
	
	public List<SearchResult> getSearchResults(String titleTerm);
}