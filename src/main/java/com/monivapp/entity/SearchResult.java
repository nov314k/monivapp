package com.monivapp.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

	@JsonProperty("Search")
	private List<SearchResultMovie> search;
	
	@JsonProperty("totalResults")
	private String totalResults;
			
	@JsonProperty("Response")
	private String response;
	
	private String searchTerm;
	
	public SearchResult() {
		
	}

	public SearchResult(List<SearchResultMovie> search, String totalResults, String response, String searchTerm) {
		this.search = search;
		this.totalResults = totalResults;
		this.response = response;
		this.searchTerm = searchTerm;
	}

	public List<SearchResultMovie> getSearch() {
		return search;
	}

	public void setSearch(List<SearchResultMovie> search) {
		this.search = search;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}