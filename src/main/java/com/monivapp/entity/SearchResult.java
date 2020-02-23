package com.monivapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Year")
	private String year;
		
	@JsonProperty("Poster")
	private String poster;
	
	@JsonProperty("imdbID")
	private String imdbId;
	
	@JsonProperty("Type")
	private String type;
	
	public SearchResult() {
		
	}

	public SearchResult(String title, String year, String poster, String imdbId, String type) {

		this.title = title;
		this.year = year;
		this.poster = poster;
		this.imdbId = imdbId;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SearchResult [title=" + title + ", year=" + year + ", poster=" + poster + ", imdbId=" + imdbId
				+ ", type=" + type + "]";
	}
}