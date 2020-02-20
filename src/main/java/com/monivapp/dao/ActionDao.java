package com.monivapp.dao;

import java.util.List;

import com.monivapp.entity.Action;

public interface ActionDao {

	public List<Action> getActions();

	public void saveAction(Action theAction);
	
}
