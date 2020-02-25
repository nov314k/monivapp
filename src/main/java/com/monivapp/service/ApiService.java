package com.monivapp.service;

import com.monivapp.entity.Detail;
import com.monivapp.entity.SearchResult;

public interface ApiService {

	public Detail getDetail(String title);
	
	public Detail getPreview(String imdbId);
	
	public SearchResult getSearchResult(String titleTerm);
}