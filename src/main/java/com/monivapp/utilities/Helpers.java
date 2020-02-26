package com.monivapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.monivapp.service.ActionService;

public class Helpers {
	
	// TODO Move these to Settings class (new)
	public static final int MAX_VOTES = 3;
	public static final int MAX_SUGGESTIONS = 3;
	public static final int RECENT_MONTHS = -1;
	public static final String ACTION_ADDED = "ADDED";
	public static final String ACTION_VOTED = "VOTED";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String USERNAME_ANONYMOUS = "ANON";
	

	public static String getRecentFromDate() {
		
		Date currentDate = new Date();
        Calendar theCalendar = Calendar.getInstance();
        theCalendar.setTime(currentDate);
        theCalendar.add(Calendar.MONTH, Helpers.RECENT_MONTHS);
        return new SimpleDateFormat(Helpers.DATE_FORMAT).format(theCalendar.getTime());
	}
	
	public static String getTodaysDate() {
		
		return new SimpleDateFormat(Helpers.DATE_FORMAT).format(new Date());
	}
	
	public static String formatSearchTerm(String searchTerm) {

		return searchTerm.replace(' ', '+').toLowerCase();
	}
	
	public static String getCurrentPrincipalName() {
	
		Authentication authentication;	
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Helpers.USERNAME_ANONYMOUS;
		} else {
			return authentication.getName();
		}
	}
	
	public static boolean isAddingQuotaExceeded(ActionService actionService) {
		
		return isQuotaExceeded(actionService, Helpers.ACTION_ADDED,
				Helpers.MAX_SUGGESTIONS);
	}
	
	public static boolean isVotingQuotaExceeded(ActionService actionService) {
		
		return isQuotaExceeded(actionService, Helpers.ACTION_VOTED, Helpers.MAX_VOTES);
	}
	
	public static boolean isQuotaExceeded(ActionService actionService, String actionType,
			int maxNumofAllowedRecentActions) {
		
		int numofRecentActions = actionService.getNumofRecentActions(
				Helpers.getCurrentPrincipalName(), actionType, Helpers.getRecentFromDate());
		int numofRemainingActions =
				maxNumofAllowedRecentActions - numofRecentActions;
		if (numofRemainingActions <= 0) {
			return true;
		}
		return false;
	}
}