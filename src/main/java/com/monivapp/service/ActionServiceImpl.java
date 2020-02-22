package com.monivapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monivapp.dao.ActionDao;
import com.monivapp.entity.Action;
import com.monivapp.entity.Movie;

@Service
@PropertySource("classpath:application.properties")
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	
	@Override
	@Transactional
	public int getNumofRecentActions(String userName, String userAction, String fromDate) {
		return actionDao.getNumofRecentActions(userName, userAction, fromDate);
	}

	@Override
	@Transactional
	public void saveAction(Action theAction) {
		actionDao.saveAction(theAction);
	}
	
	@Override
	@Transactional
	public void updateAction(Action theAction) {
		actionDao.updateAction(theAction);
	}

	@Override
	@Transactional
	public List<Action> getActions() {
		return actionDao.getActions();
	}
	
	@Override
	@Transactional
	public void deleteAction(int theId) {
		actionDao.deleteAction(theId);
	}
	
	@Override
	@Transactional
	public Action getAction(int theId) {
		return actionDao.getAction(theId);
	}
}