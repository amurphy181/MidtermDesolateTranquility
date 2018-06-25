package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	GameDAO gameDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean joinEvent(User user, Event event) {
		Event managedEvent = em.find(Event.class, event.getId());
		User addUser = em.find(User.class, user.getId());
		boolean added = false;
		if (!managedEvent.getUsers().contains(user)) {
			managedEvent.addUser(addUser);
			added = true;
		}
		
		
		return added;
	}
	
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
		}
		
		return eventList;
	}
	
	@Override
	public List<User> listAllUsers() {
		String query = "SELECT u FROM User u";
		List<User> userList = em.createQuery(query, User.class).getResultList();
		
		return userList;
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
	public User findUserByUserID(int id) {
		User found = em.find(User.class, id);
		
		return found;
	}
	
	@Override
	public User findUserByUsername(String userName) {
		String query = "select u from User u";
		User confirmed = null;
		List<User> allUsers = em.createQuery(query, User.class).getResultList();
		for (User user : allUsers) {
			if (user.getUserName().equals(userName)) {
				confirmed = em.find(User.class, user.getId());
			}
		}
		return confirmed;
	}
	
	// deactivate and reactivate users
	
	@Override
	public boolean deactivateUser(int id) {
		System.out.println("&&&&&& USER DEACTIVATE &&&&&&");
		User userToDeactivate = em.find(User.class, id);
		userToDeactivate.setStatus(false);
		em.flush();
		
		if(em.find(User.class, userToDeactivate.getId()).equals(false)) {
			System.out.println(userToDeactivate);
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean reactivateUser(int id) {
		System.out.println("@@@@@ USER REACTIVATE @@@@@");
		User userToReactivate = em.find(User.class, id);
		userToReactivate.setStatus(true);
		em.flush();
		
		if(em.find(User.class, userToReactivate.getId()).equals(true)) {
			System.out.println(userToReactivate);
			return true;
		}
		return false;
		
	}
	
	// activate Admin status for users
	@Override
	public boolean deactivateAdmin(int id) {
		System.out.println("&&&&&& ADMIN DEACTIVATE &&&&&&");
		User adminToDeactivate = em.find(User.class, id);
		adminToDeactivate.setAdmin(false);
		em.flush();
		
		if(em.find(User.class, adminToDeactivate.getId()).equals(false)) {
			System.out.println(adminToDeactivate);
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean activateAdmin(int id) {
		System.out.println("@@@@@ ADMIN ACTIVATE @@@@@");
		User userChangeToAdmin = em.find(User.class, id);
		userChangeToAdmin.setAdmin(true);
		em.flush();
		
		if(em.find(User.class, userChangeToAdmin.getId()).equals(true)) {
			System.out.println(userChangeToAdmin);
			return true;
		}
		return false;
		
	}

}
