package com.monivapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
import com.monivapp.utilities.Helpers;

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
	
	int numofRecentVotes;
	int numofRecentAdditions;
	
	@GetMapping("/list")
	public String listMovies(Model theModel) {
		
		List<Movie> theMovies = movieService.getMovies();
		theModel.addAttribute("movies", theMovies);
		
		numofRecentVotes = actionService.getNumofRecentActions(
				Helpers.getCurrentPrincipalName(), Helpers.ACTION_VOTED,
				Helpers.getRecentFromDate());
		numofRecentAdditions = actionService.getNumofRecentActions(
				Helpers.getCurrentPrincipalName(), Helpers.ACTION_ADDED,
				Helpers.getRecentFromDate());
		
		theModel.addAttribute("numofRemainingVotes",
				Helpers.MAX_VOTES - numofRecentVotes);
		theModel.addAttribute("numofRemainingAdditions",
				Helpers.MAX_SUGGESTIONS - numofRecentAdditions);

		return "movie/list";
	}
	
	@PostMapping("/add")
	public String saveMovie(Model theModel, @ModelAttribute("movie") Movie theMovie) {
		
		if (Helpers.isAddingQuotaExceeded(actionService)) {
			return "redirect:/movie/list";
		} else if (theMovie.getTitle() == null || theMovie.getTitle() == "") {
			theModel.addAttribute("errorMessage", "Please enter a movie title");
			return "movie/addForm";
		} else if (movieService.isTitleDuplicate(theMovie.getTitle())) {
			theModel.addAttribute("errorMessage",
					"Movie with the same title has already been suggested");
			return "movie/addForm";
		} else {
			theMovie.setId(0);
			theMovie.setVotes(0);
			movieService.saveMovie(theMovie);
			Action theAction = new Action(Helpers.getCurrentPrincipalName(),
					Helpers.ACTION_ADDED, theMovie.getId(), Helpers.getTodaysDate());
			actionService.saveAction(theAction);		
			return "redirect:/movie/list";
		}
	}
	
	@GetMapping("/vote")
	public String vote(@RequestParam("movieId") int theId) {
		
		if (!movieService.isIdValid(theId)) {
			return "redirect:/movie/list";
		} else if (Helpers.isVotingQuotaExceeded(actionService)) {
			return "redirect:/movie/list";
		} else {
			movieService.vote(theId);
			Action theAction = new Action(Helpers.getCurrentPrincipalName(),
					Helpers.ACTION_VOTED, theId, Helpers.getTodaysDate());
			actionService.saveAction(theAction);
			return "redirect:/movie/list";
		}
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
		Detail theDetail = apiService.getDetail(theMovie.retrieveParametrizedTitle());
		theModel.addAttribute("detail", theDetail);
		return "movie/detail";
	}
	
	@GetMapping("/preview")
	public String previewMovie(Model theModel, @RequestParam("imdbId") String imdbId) {
		
		Detail theDetail = apiService.getPreview(imdbId);
		theModel.addAttribute("detail", theDetail);
		return "search/preview";
	}
	
	@GetMapping("/addForm")
	public String showAddForm(Model theModel,
			@RequestParam("confirmMessage") String confirmMessage,
			@RequestParam("title") String title) {
		
		Movie theMovie = new Movie();
		theMovie.setTitle(title);
		theModel.addAttribute("movie", theMovie);
		theModel.addAttribute("confirmMessage", confirmMessage);
		return "movie/addForm";
	}
	
	@GetMapping("/updateForm")
	public String showUpdateForm(@RequestParam("movieId") int theId, Model theModel) {
		
		Movie theMovie = movieService.getMovie(theId);	
		theModel.addAttribute("movie", theMovie);
		return "movie/updateForm";
	}
}