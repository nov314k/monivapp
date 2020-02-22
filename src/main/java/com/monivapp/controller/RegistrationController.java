package com.monivapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monivapp.entity.User;
import com.monivapp.service.UserService;
import com.monivapp.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/registerForm")
	public String showRegisterForm (Model theModel) {	
		theModel.addAttribute("crmUser", new CrmUser());
		return "registerForm";
	}

	@PostMapping("/register")
	public String register (
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		String userName = theCrmUser.getUserName();
		if (theBindingResult.hasErrors()){
			return "registerForm";
	    }

		// Check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists");

        	return "registerForm";
        }
        // Create user account        						
        userService.save(theCrmUser);
        theModel.addAttribute("registrationSuccess",
        		"You have registered successfully<br />Please sign in to Mo-Ni-V-App!");
 
        return "loginForm";		
	}
}
