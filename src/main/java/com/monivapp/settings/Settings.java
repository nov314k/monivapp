package com.monivapp.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class Settings {
	
	@Value("${max.votes}")
	static private int maxVotes;
	public static final int MAX_VOTES = maxVotes;
	
	@Value("${max.suggestions}")
	static private int maxSuggestions;
	public static final int MAX_SUGGESTIONS = maxSuggestions;
	
	@Value("${recent.months}")
	static private int recentMonths;
	public static final int RECENT_MONTHS = recentMonths;
	
	@Value("${omdb.api.url}")
	static private String omdbApiUrl;
	public static final String OMDB_API_URL = omdbApiUrl;
	
	@Value("${username.anonymous}")
	static private String usernameAnonymous;
	public static final String USERNAME_ANONYMOUS = usernameAnonymous;
	
	@Value("${action.added}")
	static private String actionAdded;
	public static final String ACTION_ADDED = actionAdded;
	
	@Value("${action.voted}")
	static private String actionVoted;
	public static final String ACTION_VOTED = actionVoted;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
}
