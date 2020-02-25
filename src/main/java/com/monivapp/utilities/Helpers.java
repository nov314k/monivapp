package com.monivapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.monivapp.service.ActionService;

public class Helpers {
	
	// TODO Extract Calendar.MONTH, yyyy-MM-dd, + to props; add Calendar.DAY
	
	public static String getFromDate() {
		
		Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, -1);
        Date fromDate = c.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
	}
	
	public static String getTodaysDate() {
		
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static String formatTitle(String title) {
		
		return title.replace(' ', '+').toLowerCase();
	}
	
	public static String getCurrentPrincipalName() {
	
		String currentPrincipalName;
		Authentication authentication;
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			currentPrincipalName = "ANON";
			// TODO Consider currentPrincipalName = env.getProperty("user.anonymous");
		} else {
			currentPrincipalName = authentication.getName();
		}
		return currentPrincipalName;
	}
}