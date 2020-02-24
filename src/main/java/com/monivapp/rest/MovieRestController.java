package com.monivapp.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private Environment env;
	
	private String keywordVoted;
	private String keywordAdded;
	private Authentication authentication;
	private String currentPrincipalName;

	private int numofRecentVotes;
	private int numofRecentAdditions;
	private int maxNumofAllowedRecentVotes;
	private int numofRemainingVotes;
	private int maxNumofAllowedRecentAdditions;
	private int numofRemainingAdditions;
	
	@PostConstruct
	protected void init() {

		keywordVoted = env.getProperty("keyword.voted");
		keywordAdded = env.getProperty("keyword.added");
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			currentPrincipalName = env.getProperty("user.anonymous");
		} else {
			currentPrincipalName = authentication.getName();
		}
		
		maxNumofAllowedRecentVotes = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentVotes"));
		maxNumofAllowedRecentAdditions = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentAdditions"));
	}
	
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
		
		// NOTE No title checks are done here (some come from search, some assumed OK)
		// TODO Add checks for duplicate titles (movies)
		if (isAddingQuotaExceeded()) {
			throw new MovieAddingQuotaException(
					"You cannot add any more movies (quota exceeded)");
		} else {
			theMovie.setId(0);
			theMovie.setVotes(0);
			movieService.saveMovie(theMovie);
			Action theAction = new Action(currentPrincipalName, keywordAdded,
					theMovie.getId(), getTodaysDate());
			actionService.saveAction(theAction);
		}
		return theMovie;
	}
	
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
		if (isVotingQuotaExceeded()) {
			throw new MovieVotingQuotaException(
					"You cannot cast any more votes (quota exceeded)");
		} else {
			movieService.vote(movieId);
			Action theAction = new Action(currentPrincipalName, keywordVoted,
					movieId, getTodaysDate());
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
	
	// TODO Remove after testing
	@GetMapping("/movies/reset")
	public String resetActions() {
		
		actionService.resetActions();
		return "Actions successfully cleared (quotas reset)";
	}
	
	private String getFromDate() {
		
		Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // TODO Extract time period out to the properties file (include DAYS also)
        c.add(Calendar.MONTH, -1);
        Date fromDate = c.getTime();
        // TODO Extract date format out to the properties file
        return new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
	}
	
	private String getTodaysDate() {
		
        // TODO Extract date format out to the properties file
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	private boolean isAddingQuotaExceeded() {
		numofRecentAdditions = actionService.getNumofRecentActions(
				currentPrincipalName, keywordAdded, getFromDate());
		numofRemainingAdditions =
				maxNumofAllowedRecentAdditions - numofRecentAdditions;
		if (numofRemainingAdditions <= 0) {
			return true;
		}
		return false;
	}
	
	private boolean isVotingQuotaExceeded() {
		numofRecentVotes = actionService.getNumofRecentActions(
				currentPrincipalName, keywordVoted, getFromDate());
		numofRemainingVotes = maxNumofAllowedRecentVotes - numofRecentVotes;
		if (numofRemainingVotes <= 0) {
			return true;
		}
		return false;
	}
}