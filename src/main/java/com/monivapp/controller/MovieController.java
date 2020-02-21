package com.monivapp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monivapp.entity.Movie;
import com.monivapp.service.ActionService;
import com.monivapp.service.MovieService;

@Controller
@RequestMapping("/movie")
@PropertySource("classpath:monivapp.properties")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private Environment env;
	
	boolean userCanVote = false;
	boolean userCanAddMovies = false;
	
	@GetMapping("/list")
	public String listMovies(Model theModel) {
		List<Movie> theMovies = movieService.getMovies();
		theModel.addAttribute("movies", theMovies);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		int numofRecentVotes = actionService.getNumofRecentActions(
				currentPrincipalName, env.getProperty("keyword.voted"), getFromDate());
		int numofRecentAdditions = actionService.getNumofRecentActions(
				currentPrincipalName, env.getProperty("keyword.added"), getFromDate());
		
		int maxNumofAllowedRecentVotes = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentVotes"));
		int numofRemainingVotes = maxNumofAllowedRecentVotes - numofRecentVotes;
		if (numofRemainingVotes > 0) {
			theModel.addAttribute("userCanVote", true);
		} else {
			theModel.addAttribute("userCanVote", false);
		}
		theModel.addAttribute("numofRemainingVotes", numofRemainingVotes);
		
		int maxNumofAllowedRecentAdditions = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentAdditions"));
		int numofRemainingAdditions = maxNumofAllowedRecentAdditions - numofRecentAdditions;
		if (numofRemainingAdditions > 0) {
			theModel.addAttribute("userCanAddMovies", true);
		} else {
			theModel.addAttribute("userCanAddMovies", false);
		}
		theModel.addAttribute("numofRemainingAdditions", numofRemainingAdditions);

		return "movies-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Movie theMovie = new Movie();
		theModel.addAttribute("movie", theMovie);
		return "add-movie-form";
	}
	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie theMovie) {
		movieService.saveMovie(theMovie);	
		return "redirect:/movie/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("movieId") int theId, Model theModel) {
		Movie theMovie = movieService.getMovie(theId);	
		theModel.addAttribute("movie", theMovie);
		return "add-movie-form";
	}
	
	@GetMapping("/delete")
	public String deleteMovie(@RequestParam("movieId") int theId) {
		movieService.deleteMovie(theId);
		return "redirect:/movie/list";
	}
	
	@GetMapping("/vote")
	public String vote(@RequestParam("movieId") int theId) {
		movieService.vote(theId);
		return "redirect:/movie/list";
	}
	
	private String getFromDate() {
		Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, -1);
        Date fromDate = c.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
	}
}