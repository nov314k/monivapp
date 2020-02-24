package com.monivapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monivapp.service.ApiService;

@Controller
@RequestMapping("/search")
@PropertySource("classpath:application.properties")
public class SearchResultController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping
	public String search(Model theModel) {
		
		return "redirect:/movie/list";
	}
	
	@GetMapping("/searchForm")
	public String showSearchForm(Model theModel) {
		
		return "redirect:/movie/list";
	}
}