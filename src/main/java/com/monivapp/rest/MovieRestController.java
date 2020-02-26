package com.monivapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp.entity.Action;
import com.monivapp.entity.Movie;
import com.monivapp.service.ActionService;
import com.monivapp.service.MovieService;
import com.monivapp.utilities.Helpers;

@RestController
//TODO Adjust CrossOrigin policy after testing
@CrossOrigin
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ActionService actionService;
	
	@GetMapping("/movies")
	public List<Movie> getMovies() {
		
		return movieService.getMovies();
	}
	
	@GetMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable int movieId) {
		
		Movie theMovie = movieService.getMovie(movieId);
		if (theMovie == null) {
			throw new MovieNotFoundException("Movie id not found: " + movieId);
		}
		return theMovie;
	}
	
	@PostMapping("/movies")
	public Movie addMovie (@RequestBody Movie theMovie) {
		
		if (Helpers.isAddingQuotaExceeded(actionService)) {
			throw new MovieAddException(
					"You cannot suggest any more movies (quota exceeded)");
		} else if (theMovie.getTitle() == null || theMovie.getTitle() == "") {
			throw new MovieAddException(
					"Movie with no title cannot be added");
		} else if (movieService.isTitleDuplicate(theMovie.getTitle())) {
			throw new MovieAddException(
					"Movie with the same title has already been suggested");
		} else {
			theMovie.setId(0);
			theMovie.setVotes(0);
			movieService.saveMovie(theMovie);
			Action theAction = new Action(Helpers.getCurrentPrincipalName(),
					Helpers.ACTION_ADDED, theMovie.getId(), Helpers.getTodaysDate());
			actionService.saveAction(theAction);
		}
		return theMovie;
	}
	
	// TODO Remove after testing (for testing only)
	@PutMapping("/movies")
	public Movie updateMovie (@RequestBody Movie theMovie) {
		
		movieService.updateMovie(theMovie);
		return theMovie;
	}
	
	@GetMapping("/movies/vote/{movieId}")
	public Movie voteForMovie (@PathVariable int movieId) {
		
		Movie theMovie = movieService.getMovie(movieId);
		if (theMovie == null) {
			throw new MovieNotFoundException("Movie id not found: " + movieId);
		}
		if (Helpers.isVotingQuotaExceeded(actionService)) {
			throw new MovieVoteQuotaException(
					"You cannot cast any more votes (quota exceeded)");
		} else {
			movieService.vote(movieId);
			Action theAction = new Action(Helpers.getCurrentPrincipalName(),
					Helpers.ACTION_VOTED, movieId, Helpers.getTodaysDate());
			actionService.saveAction(theAction);
		}
		theMovie = movieService.getMovie(movieId);
		return theMovie;
	}
	
	@DeleteMapping("/movies/{movieId}")
	public String deleteMovie (@PathVariable int movieId) {
		
		Movie tempMovie = movieService.getMovie(movieId);
		if (tempMovie == null) {
			throw new MovieNotFoundException("Movie id not found: " + movieId);
		}
		movieService.deleteMovie(movieId);
		return "Deleted movie id: " + movieId; 
	}
	
	// TODO Remove after testing (for testing only)
	@GetMapping("/movies/reset")
	public String resetActions() {
		
		actionService.resetActions();
		return "Action table successfully truncated (quotas reset)";
	}
}