package com.monivapp.dao;

import com.monivapp.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
