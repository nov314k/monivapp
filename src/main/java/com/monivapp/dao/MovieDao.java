package com.monivapp.dao;

import java.util.List;

import com.monivapp.entity.Movie;

public interface MovieDao {

	public List<Movie> getMovies();
	
	public Movie getMovie(int theId);

	public void deleteMovie(int theId);
	
	public void saveMovie(Movie theMovie);
	
	public void updateMovie(Movie theMovie);
	
	public void vote(int theId);
	
	public boolean isTitleDuplicate(String title);
	
	public boolean isIdValid(int theId);
}