package com.monivapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monivapp.entity.Movie;
import com.monivapp.entity.SearchResult;
import com.monivapp.service.ApiService;
import com.monivapp.utilities.Helpers;

@Controller
@RequestMapping("/search")
@PropertySource("classpath:application.properties")
public class SearchController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("searchResult", new SearchResult());
		return "search/searchForm";
	}

	@PostMapping("/searchOmdb")
	public String submitForm(@ModelAttribute("searchResult") SearchResult searchResult,
			BindingResult result, ModelMap model) {
		
		if (searchResult.getSearchTerm() == null || searchResult.getSearchTerm() == "") {
			model.addAttribute("errorMessage", "Please enter a search term");
			return "search/searchForm";
		}
		
		SearchResult theSearchResult = apiService.getSearchResult(
				Helpers.formatSearchTerm(searchResult.getSearchTerm()));
		model.addAttribute("search", theSearchResult.getSearch());
		model.addAttribute("totalResults", theSearchResult.getTotalResults());
		model.addAttribute("response", theSearchResult.getResponse());
	    return "search/searchForm";
	}
}