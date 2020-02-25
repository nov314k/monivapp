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

import com.monivapp.entity.SearchResult;
import com.monivapp.service.ApiService;

@Controller
@RequestMapping("/search")
@PropertySource("classpath:application.properties")
public class SearchController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/search")
	public String showForm(Model theModel) {
		theModel.addAttribute("searchResult", new SearchResult());
		return "search/searchForm";
	}

	@PostMapping("/search")
	public String submitForm(@ModelAttribute("searchResult") SearchResult searchResult,
			BindingResult result, ModelMap model) {
		
		SearchResult theSearchResult = apiService.getSearchResult(formatTitle(searchResult.getSearchTerm()));
		model.addAttribute("search", theSearchResult.getSearch());
		model.addAttribute("totalResults", theSearchResult.getTotalResults());
		model.addAttribute("response", theSearchResult.getResponse());
	    return "search/searchForm";
	}
	
	private String formatTitle(String title) {

		// TODO Extract the separator symbol to the properties file
		return title.replace(' ', '+').toLowerCase();
	}
}