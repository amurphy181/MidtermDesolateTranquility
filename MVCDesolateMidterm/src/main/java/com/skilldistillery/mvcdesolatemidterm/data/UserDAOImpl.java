package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	GameDAO gameDao;

	@Autowired
	private PasswordEncoder encoder;

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean joinEvent(User user, Event event) {
		Event managedEvent = em.find(Event.class, event.getId());
		User addUser = em.find(User.class, user.getId());
		boolean added = false;
		if (!managedEvent.getUsers().contains(user)) {
			managedEvent.addUser(addUser);
			addUser.addEvent(managedEvent);
			added = true;
		}

		return added;
	}

	@Override
	public boolean leaveEvent(User user, Event event) {
		Event managedEvent = em.find(Event.class, event.getId());
		User removeUser = em.find(User.class, user.getId());
		boolean removed = false;
		if (!managedEvent.getUsers().contains(user)) {
			managedEvent.removeUser(removeUser);
			removeUser.removeEvent(managedEvent);
			removed = true;
		}

		return removed;
	}

	@Override
	public User create(User user) {
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setStatus(true);
		user.setPictureURL(
				"https://ctl.s6img.com/society6/img/tMR7zIQiWAu0X61keq7sDb1iBJA/w_700/prints/~artwork/s6-0069/a/28296935_4974427/~~/one-punch-man-saitama-face-2-prints.jpg");

		em.persist(user);
		em.flush();
		return user;
	}

	@Override
	public List<Event> listAllEvents() {
		Date now = new Date();
		String query = "select e from Event e";
		List<Event> eventList = em.createQuery(query, Event.class).getResultList();
		eventList.sort(Comparator.comparing(o -> o.getStartDate()));
		Collections.reverse(eventList);
		//checking milli seconds since 1970 for current datetime and event datetime to see if the event is 12 hours old. ... seriously.  
		for (Event event : eventList) {
			Long eventMilli = event.getStartDate().getTime();
			Long nowMilli = now.getTime();

			if (nowMilli - eventMilli > (12 * 60 * 60 * 1000))
				event.setStatus(false);
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
			if (user.getUserName().equalsIgnoreCase(userName)) {
				unique = false;
			}
		}
		return unique;
	}

	@Override
	public User update(int id, User updatedUser) {
		User managed = em.find(User.class, id);
		managed.setUserName(updatedUser.getUserName());
		String encryptedPassword = encoder.encode(updatedUser.getPassword());
		managed.setPassword(encryptedPassword);
		em.flush();
		return managed;
	}

	@Override
	public boolean passwordConfirmation(User user, String password) {

		String query = "SELECT u FROM User u WHERE u.userName = :name";
		User managedUser = em.createQuery(query, User.class).setParameter("name", user.getUserName()).getSingleResult();
		if (encoder.matches(password, managedUser.getPassword())) {
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
			if (user.getUserName().equalsIgnoreCase(userName)) {
				confirmed = em.find(User.class, user.getId());
			}
		}
		return confirmed;
	}

	// deactivate and reactivate users

	@Override
	public boolean deactivateUser(int id) {
		User userToDeactivate = em.find(User.class, id);
		userToDeactivate.setStatus(false);
		em.flush();

		if (em.find(User.class, userToDeactivate.getId()).equals(false)) {
			System.out.println(userToDeactivate);
			return true;
		}
		return false;

	}

	@Override
	public boolean reactivateUser(int id) {
		User userToReactivate = em.find(User.class, id);
		userToReactivate.setStatus(true);
		em.flush();

		if (em.find(User.class, userToReactivate.getId()).equals(true)) {
			System.out.println(userToReactivate);
			return true;
		}
		return false;

	}

	// activate Admin status for users
	@Override
	public boolean deactivateAdmin(int id) {
		User adminToDeactivate = em.find(User.class, id);
		adminToDeactivate.setAdmin(false);
		em.flush();

		if (em.find(User.class, adminToDeactivate.getId()).equals(false)) {
			return true;
		}
		return false;

	}

	@Override
	public boolean activateAdmin(int id) {
		User userChangeToAdmin = em.find(User.class, id);
		userChangeToAdmin.setAdmin(true);
		em.flush();
		if (em.find(User.class, userChangeToAdmin.getId()).equals(true)) {
			return true;
		}
		return false;

	}

	@Override
	public boolean setNewPassword(int id, String newPassword) {
		User managedUser = em.find(User.class, id);
		if (encoder.matches(newPassword, managedUser.getPassword())) {
			return false;
		} else {
			String encryptedPassword = encoder.encode(newPassword);
			managedUser.setPassword(encryptedPassword);

			return true;
		}
	}

	@Override
	public boolean setProfileBlurb(int id, String blurb) {
		if (blurb.length() > 140) {
			return false;
		} else {
			User setUserBlurb = em.find(User.class, id);
			setUserBlurb.setSummary(blurb);
			return true;
		}

	}

	@Override
	public boolean setProfilePicture(int id, String picURL) {
		User userPicUpdate = em.find(User.class, id);

		if (picURL.equals(userPicUpdate.getPictureURL())) {
			em.flush();
			return false;
		} else {

			em.persist(userPicUpdate);
			em.flush();
			return true;
		}

	}

}
