package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User create(User user) {

		em.persist(user);
		em.flush();
		return user;
	}
	@Override
	public List<Event> listAllEvents() {
		String query = "select e from Event e";
		List<Event> eventList = em.createQuery(query, Event.class).getResultList();	
		for (Event event : eventList) {
			System.out.println(event);
		}
		
		return eventList;
	}
	@Override
	public boolean uniqueUsername(String userName) {
		String query = "select u from User u";
		boolean unique = true;
		List<User> allUsers = em.createQuery(query, User.class).getResultList();
		for (User user : allUsers) {
			if(user.getUserName().equals(userName)) {
				unique = false;
			}
		}
		return unique;
	}

	
	@Override
	public User update(int id, User updatedUser) {

		User managed = em.find(User.class, id);
		
		managed.setUserName(updatedUser.getUserName());
		managed.setPassword(updatedUser.getPassword());

		em.flush();

		return managed;
	}

	@Override
	public boolean passwordConfirmation(User user, String password) {
		
		String properPassword = user.getPassword();
		
		if (properPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	  }
	
	@Override
	public User findUserByUsername(String userName) {
		String query = "select u from User u";
		User confirmed = null;
		List<User> allUsers = em.createQuery(query, User.class).getResultList();
		for (User user : allUsers) {
			if (user.getUserName().equals(userName)) {
				confirmed = user;
			}
		}
		
		return confirmed;
	}

}
