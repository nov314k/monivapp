package com.monivapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monivapp.entity.Action;

@Repository
public class ActionDaoImpl implements ActionDao {

	@Autowired
	private SessionFactory sessionFactory;
			
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
}