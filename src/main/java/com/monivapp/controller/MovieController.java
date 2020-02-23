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

import com.monivapp.entity.Action;
import com.monivapp.entity.Detail;
import com.monivapp.entity.Movie;
import com.monivapp.service.ActionService;
import com.monivapp.service.ApiService;
import com.monivapp.service.MovieService;

@Controller
@RequestMapping("/movie")
@PropertySource("classpath:application.properties")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private Environment env;
	
	private String keywordVoted;
	private String keywordAdded;
	private Authentication authentication;
	private String currentPrincipalName;
	
	@GetMapping("/list")
	public String listMovies(Model theModel) {
		
		this.keywordVoted = env.getProperty("keyword.voted");
		this.keywordAdded = env.getProperty("keyword.added");
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
		this.currentPrincipalName = authentication.getName();
		
		List<Movie> theMovies = movieService.getMovies();
		theModel.addAttribute("movies", theMovies);
		
		int numofRecentVotes = actionService.getNumofRecentActions(
				currentPrincipalName, keywordVoted, getFromDate());
		int numofRecentAdditions = actionService.getNumofRecentActions(
				currentPrincipalName, keywordAdded, getFromDate());
		
		// Calculate remaining votes
		int maxNumofAllowedRecentVotes = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentVotes"));
		int numofRemainingVotes = maxNumofAllowedRecentVotes - numofRecentVotes;
		theModel.addAttribute("numofRemainingVotes", numofRemainingVotes);
		
		// Calculate remaining movie suggestions		
		int maxNumofAllowedRecentAdditions = Integer.parseInt(
				env.getProperty("maxNumofAllowedRecentAdditions"));
		int numofRemainingAdditions =
				maxNumofAllowedRecentAdditions - numofRecentAdditions;
		theModel.addAttribute("numofRemainingAdditions", numofRemainingAdditions);

		return "movie/list";
	}
	
	@PostMapping("/add")
	public String saveMovie(@ModelAttribute("movie") Movie theMovie) {
		
		movieService.saveMovie(theMovie);
		Action theAction = new Action(currentPrincipalName, keywordAdded,
				theMovie.getId(), getTodaysDate());
		actionService.saveAction(theAction);		
		return "redirect:/movie/list";
	}
	
	@GetMapping("/vote")
	public String vote(@RequestParam("movieId") int theId) {
		
		movieService.vote(theId);
		Action theAction = new Action(currentPrincipalName, keywordVoted, theId,
				getTodaysDate());
		actionService.saveAction(theAction);
		return "redirect:/movie/list";
	}	
	
	@GetMapping("/delete")
	public String deleteMovie(@RequestParam("movieId") int theId) {
		
		movieService.deleteMovie(theId);
		return "redirect:/movie/list";
	}
	
	@PostMapping("/update")
	public String updateMovie(@ModelAttribute("movie") Movie theMovie) {
		
		movieService.saveMovie(theMovie);
		return "redirect:/movie/list";
	}
	
	@GetMapping("/detail")
	public String detailMovie(Model theModel, @RequestParam("movieId") int theId) {
		
		Movie theMovie = movieService.getMovie(theId);	
		theModel.addAttribute("movie", theMovie);
		Detail theDetail = apiService.getDetail(formatTitle(theMovie.getTitle()));
		theModel.addAttribute("detail", theDetail);
		return "movie/detail";
	}
	
	@GetMapping("/addForm")
	public String showAddForm(Model theModel) {
		
		Movie theMovie = new Movie();
		theModel.addAttribute("movie", theMovie);
		return "movie/addForm";
	}
	
	@GetMapping("/updateForm")
	public String showUpdateForm(@RequestParam("movieId") int theId, Model theModel) {
		
		Movie theMovie = movieService.getMovie(theId);	
		theModel.addAttribute("movie", theMovie);
		return "movie/updateForm";
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
	
	private String formatTitle(String title) {

		// TODO Extract the separator symbol to the properties file
		return title.replace(' ', '+').toLowerCase();
	}
}