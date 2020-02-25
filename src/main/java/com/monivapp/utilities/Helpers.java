package com.monivapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.monivapp.service.ActionService;
import com.monivapp.settings.Settings;

public class Helpers {

	public static String getRecentFromDate() {
		
		Date currentDate = new Date();
        Calendar theCalendar = Calendar.getInstance();
        theCalendar.setTime(currentDate);
        theCalendar.add(Calendar.MONTH, Settings.RECENT_MONTHS);
        return new SimpleDateFormat(Settings.DATE_FORMAT).format(theCalendar.getTime());
	}
	
	public static String getTodaysDate() {
		
		return new SimpleDateFormat(Settings.DATE_FORMAT).format(new Date());
	}
	
	public static String getCurrentPrincipalName() {
	
		Authentication authentication;	
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Settings.USERNAME_ANONYMOUS;
		} else {
			return authentication.getName();
		}
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
	
	public static boolean isAddingQuotaExceeded(ActionService actionService) {
		return isQuotaExceeded(actionService, Settings.ACTION_ADDED,
				Settings.MAX_SUGGESTIONS);
	}
	
	public static boolean isVotingQuotaExceeded(ActionService actionService) {
		return isQuotaExceeded(actionService, Settings.ACTION_VOTED,
				Settings.MAX_VOTES);
	}
}