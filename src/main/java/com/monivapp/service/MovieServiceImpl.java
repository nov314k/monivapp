package com.monivapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monivapp.dao.ActionDao;
import com.monivapp.dao.MovieDao;
import com.monivapp.entity.Action;
import com.monivapp.entity.Movie;
import com.monivapp.entity.User;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private ActionDao actionDao;
	
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
		// TODO Consider other options for getting username/id?
		// https://www.baeldung.com/get-user-in-spring-security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Action theAction = new Action(currentPrincipalName, "VOTED", theId, "2020-02-21");
		actionDao.saveAction(theAction);
	}
}