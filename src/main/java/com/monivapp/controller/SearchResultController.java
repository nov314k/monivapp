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

import com.monivapp.entity.SearchResult;
import com.monivapp.service.ApiService;

@Controller
@RequestMapping("/search")
@PropertySource("classpath:application.properties")
public class SearchResultController {
	
	@Autowired
	private ApiService apiService;
	
	@PostMapping("/search")
	public String search(Model theModel,
			@ModelAttribute("searchResult") SearchResult theSearchResult) {
		
		String title = theSearchResult.getTitle();
		if (title != null && !title.isEmpty()) {
			List<SearchResult> theSearchResults = apiService.getSearchResults(
					formatTitle(title));
			for (SearchResult sr : theSearchResults) {
				System.out.println(sr);
			}
			theModel.addAttribute("searchResults", theSearchResults);
		}
		return "search/searchForm";
	}
	
	@GetMapping("/searchForm")
	public String showSearchForm(Model theModel) {
		
		SearchResult theSearchResult = new SearchResult();
		theModel.addAttribute("searchResult", theSearchResult);
		return "search/searchForm";
	}
	
	private String formatTitle(String title) {

		// TODO Extract the separator symbol to the properties file
		// TODO DRY
		return title.replace(' ', '+').toLowerCase();
	}
}