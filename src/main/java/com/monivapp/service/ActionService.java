package com.monivapp.service;

import java.util.List;

import com.monivapp.entity.Action;

public interface ActionService {

	public List<Action> getActions();

	public void saveAction(Action theAction);
}
