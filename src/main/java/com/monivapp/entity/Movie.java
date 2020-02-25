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
	
	public String getParametrizedTitle() {
		
		return this.getTitle().replace(" ", "+").toLowerCase();
	}
	
	private String sanitizeTitle(String title) {
		
		return title.replace("&", "");
	}
	
	public Movie() {
		
	}
	
	public Movie(String title, int votes) {
		
		this.title = this.sanitizeTitle(title);
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
		title = this.sanitizeTitle(title);
		this.title = title;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
}