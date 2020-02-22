package com.monivapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monivapp.dao.MovieDao;
import com.monivapp.entity.Movie;

@Service
@PropertySource("classpath:application.properties")
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Override
	@Transactional
	public List<Movie> getMovies() {
		return movieDao.getMovies();
	}

	@Override
	@Transactional
	public void saveMovie(Movie theMovie) {
		movieDao.saveMovie(theMovie);
	}
	
	// TODO Consider combining with saveMovie
	@Override
	@Transactional
	public void updateMovie(Movie theMovie) {
		movieDao.updateMovie(theMovie);
	}

	@Override
	@Transactional
	public Movie getMovie(int theId) {
		return movieDao.getMovie(theId);
	}

	@Override
	@Transactional
	public void deleteMovie(int theId) {
		movieDao.deleteMovie(theId);
	}
	
	@Override
	@Transactional
	public void vote(int theId) {
		movieDao.vote(theId);
	}
}