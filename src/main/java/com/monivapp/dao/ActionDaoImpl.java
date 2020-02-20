package com.monivapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monivapp.entity.Action;
import com.monivapp.entity.Movie;

@Repository
public class ActionDaoImpl implements ActionDao {

	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Action> getActions() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Action> theQuery = currentSession.createQuery("from Action", Action.class);
		List<Action> actions = theQuery.getResultList();		
		return actions;
	}

	@Override
	public void saveAction(Action theAction) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theAction);
	}
}