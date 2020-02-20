package com.monivapp.dao;

import java.util.List;

import com.monivapp.entity.Movie;

public interface MovieDao {

	public List<Movie> getMovies();

	public void saveMovie(Movie theMovie);

	public Movie getMovie(int theId);

	public void deleteMovie(int theId);
	
}
