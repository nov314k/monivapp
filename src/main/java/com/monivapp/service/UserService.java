package com.monivapp.service;

import com.monivapp.entity.User;
import com.monivapp.user.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	void save(CrmUser crmUser);
	
	User findByUserName(String userName);
}