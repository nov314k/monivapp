package com.monivapp.service;

import java.util.List;

import com.monivapp.entity.Movie;

public interface MovieService {

	public List<Movie> getMovies();

	public void saveMovie(Movie theMovie);
	
	// TODO Consider combining with saveMovie
	public void updateMovie(Movie theMovie);

	public Movie getMovie(int theId);

	public void deleteMovie(int theId);	
	
	public void vote(int theId);
}