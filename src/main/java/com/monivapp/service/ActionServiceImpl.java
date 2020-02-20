package com.monivapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monivapp.dao.ActionDao;
import com.monivapp.entity.Action;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	
	@Override
	@Transactional
	public List<Action> getActions() {
		return actionDao.getActions();
	}

	@Override
	@Transactional
	public void saveAction(Action theAction) {
		actionDao.saveAction(theAction);
	}
}