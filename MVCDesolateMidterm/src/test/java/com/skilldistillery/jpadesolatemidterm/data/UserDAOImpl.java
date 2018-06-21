package com.skilldistillery.jpadesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		String query = "select u from User where s.userName = :name";
		User user = em.createQuery(query, User.class).setParameter("name", userName).getSingleResult();
		
		
		return user;
	}

}
