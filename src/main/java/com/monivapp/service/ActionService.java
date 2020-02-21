package com.monivapp.service;

import java.util.List;

import com.monivapp.entity.Action;

public interface ActionService {
	
	public int getNumofRecentActions(String userName, String userAction, String fromDate);

	public void saveAction(Action theAction);

	public List<Action> getActions();
}
