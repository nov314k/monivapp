package com.monivapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.monivapp.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/movie/list").permitAll()
			.antMatchers("/movie/add").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/movie/vote").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/movie/update").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers("/movie/delete").hasAnyRole("ADMIN")
			.antMatchers("/movie/detail").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/movie/addForm").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/movie/updateForm").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers("/movie/**").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/action/list").hasRole("ADMIN")
			.antMatchers("/action/update").hasRole("ADMIN")
			.antMatchers("/action/delete").hasRole("ADMIN")
			.antMatchers("/action/updateForm").hasRole("ADMIN")
			.antMatchers("/search/search").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			.antMatchers("/search/searchForm").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			
			// TODO Turn back on after initial Angular testing
//			.antMatchers(HttpMethod.GET, "/api/movies").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/movies/**").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
//			.antMatchers(HttpMethod.POST, "/api/movies").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
//			.antMatchers(HttpMethod.POST, "/api/movies/**").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/movies/vote").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
//			.antMatchers(HttpMethod.GET, "/api/movies/vote/**").hasAnyRole("VOTER", "MAINTAINER", "ADMIN")
			
			// TODO Remove after initial Angular testing
			.antMatchers(HttpMethod.GET, "/api/movies").permitAll()
			.antMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/movies").permitAll()
			.antMatchers(HttpMethod.POST, "/api/movies/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/movies/vote").permitAll()
			.antMatchers(HttpMethod.GET, "/api/movies/vote/**").permitAll()

			.antMatchers(HttpMethod.PUT, "/api/movies").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/movies/**").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/movies/**").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/movies/reset").hasAnyRole("MAINTAINER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/movies/reset/**").hasAnyRole("MAINTAINER", "ADMIN")

			.and()
			.httpBasic()
			
			.and()
			.csrf().disable()
			
			// NOTE Required by the MVC app
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			//.and()
			
			.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			
			.and()
			.logout().permitAll()
			
			.and()
			.exceptionHandling().accessDeniedPage("/error");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
}