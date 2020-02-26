package com.monivapp.service;

import java.util.List;

import com.monivapp.entity.Movie;

public interface MovieService {

	public List<Movie> getMovies();
	
	public Movie getMovie(int theId);

	public void saveMovie(Movie theMovie);
	
	public void deleteMovie(int theId);
	
	public void updateMovie(Movie theMovie);

	public void vote(int theId);
	
	public boolean isTitleDuplicate(String title);
}