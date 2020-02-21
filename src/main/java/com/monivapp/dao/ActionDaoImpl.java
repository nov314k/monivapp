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
	public int getNumofRecentActions(String userName, String userAction, String fromDate) {
		Session currentSession = sessionFactory.getCurrentSession();
		// NOTE Not using count(date) for testing purposes
		Query<Action> theQuery = currentSession.createQuery(
				"from Action where "
				+ "username = :userName and "
				+ "action = :userAction and "
				+ "date > :fromDate",
				Action.class);
		theQuery.setParameter("userName", userName);
		theQuery.setParameter("userAction", userAction);
		theQuery.setParameter("fromDate", fromDate);
		List<Action> actions = theQuery.getResultList();		
		return actions.size();
	}

	@Override
	public void saveAction(Action theAction) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theAction);
	}
	
	@Override
	public void updateAction(Action theAction) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(theAction);
	}
	
	@Override
	public void deleteAction(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Action where id=:actionId");
		theQuery.setParameter("actionId", theId);
		theQuery.executeUpdate();		
	}
	
	@Override
	public Action getAction(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Action theAction = currentSession.get(Action.class, theId);
		return theAction;
	}
}