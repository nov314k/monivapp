package com.monivapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monivapp.dao.ActionDao;
import com.monivapp.dao.MovieDao;
import com.monivapp.entity.Action;
import com.monivapp.entity.Movie;

@Service
@PropertySource("classpath:monivapp.properties")
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private ActionDao actionDao;
	
	@Autowired
	private Environment env;
	
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
		// TODO Consider other options for getting username/id (cf below)?
		// https://www.baeldung.com/get-user-in-spring-security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Action theAction = new Action(currentPrincipalName, env.getProperty("action.voted"),
				theId, this.getTodaysDate());
		actionDao.saveAction(theAction);
	}
	
	private String getTodaysDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}