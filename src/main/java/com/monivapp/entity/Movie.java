package com.monivapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="votes")
	private int votes;
	
	// Note Using "getParametrizedTitle()" adds the field to JSON!
	public String retrieveParametrizedTitle() {
		
		return this.getTitle().replace(" ", "+").toLowerCase();
	}
	
	private String sanitizeTitleString(String title) {
		
		return title.replace("&", "").trim();
	}
	
	public void sanitizeTitle() {
		this.setTitle(sanitizeTitleString(this.getTitle()));
	}
	
	public Movie() {
		
	}
	
	public Movie(String title, int votes) {
		
		this.title = sanitizeTitleString(title);
		this.votes = votes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		title = sanitizeTitleString(title);
		this.title = title;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
}