package com.monivapp.dao;

import java.util.List;

import com.monivapp.entity.Action;

public interface ActionDao {
	
	List<Action> getActions();
	
	public Action getAction(int theId);
	
	public void saveAction(Action theAction);
	
	public void deleteAction(int theId);
	
	public void updateAction(Action theAction);
	
	public int getNumofRecentActions(String userName, String userAction, String fromDate);
	
	public void resetActions();
}
