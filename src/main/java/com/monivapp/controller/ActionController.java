package com.monivapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monivapp.entity.Action;
import com.monivapp.service.ActionService;

@Controller
@RequestMapping("/action")
@PropertySource("classpath:monivapp.properties")
public class ActionController {

	@Autowired
	private ActionService actionService;
		
	@GetMapping("/list")
	public String listActions(Model theModel) {
		List<Action> theActions = actionService.getActions();
		theModel.addAttribute("actions", theActions);	
		return "actions-list";
	}
}