package com.monivapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monivapp.entity.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Movie> getMovies() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Movie> theQuery = currentSession.createQuery("from Movie order by votes", Movie.class);
		List<Movie> movies = theQuery.getResultList();		
		return movies;
	}

	@Override
	public void saveMovie(Movie theMovie) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theMovie);
	}

	@Override
	public Movie getMovie(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Movie theMovie = currentSession.get(Movie.class, theId);
		return theMovie;
	}

	@Override
	public void deleteMovie(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Movie where id=:movieId");
		theQuery.setParameter("movieId", theId);
		theQuery.executeUpdate();		
	}
}