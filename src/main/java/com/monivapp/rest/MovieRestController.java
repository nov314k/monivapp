package com.monivapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp.entity.Movie;
import com.monivapp.service.MovieService;

@RestController
@RequestMapping("/api") 
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
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
		
		theMovie.setId(0);
		movieService.saveMovie(theMovie);
		return theMovie;
	}
	
	@PutMapping("/movies")
	public Movie updateMovie (@RequestBody Movie theMovie) {
		
		movieService.updateMovie(theMovie);
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
}