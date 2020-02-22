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
import com.monivapp.service.ActionService;

@Controller
@RequestMapping("/action")
@PropertySource("classpath:application.properties")
public class ActionController {

	@Autowired
	private ActionService actionService;
		
	@GetMapping("/list")
	public String listActions(Model theModel) {
		List<Action> theActions = actionService.getActions();
		theModel.addAttribute("actions", theActions);	
		return "action/list";
	}
	
	@PostMapping("/update")
	public String updateAction(@ModelAttribute("action") Action theAction) {
		actionService.saveAction(theAction);
		return "redirect:/action/list";
	}
	
	@GetMapping("/delete")
	public String deleteAction(@RequestParam("actionId") int theId) {
		actionService.deleteAction(theId);
		return "redirect:/action/list";
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("actionId") int theId, Model theModel) {
		Action theAction = actionService.getAction(theId);	
		theModel.addAttribute("action", theAction);
		return "action/updateForm";
	}
}