package com.monivapp.dao;

import com.monivapp.entity.Action;

public interface ActionDao {

	public int getNumofRecentActions(String userName, String userAction, String fromDate);

	public void saveAction(Action theAction);	
}
