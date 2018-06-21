package com.skilldistillery.jpadesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User create(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.flush();
		em.getTransaction().commit();
		emf.close();
		return user;
	}

	@Override
	public User update(int id, User updatedUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		User managed = em.find(User.class, id);
		
		managed.setUserName(updatedUser.getUserName());
		managed.setPassword(updatedUser.getPassword());

		em.flush();
		em.getTransaction().commit();

		emf.close();
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
	
//	@Override
//	public User findUserByUsername(String userName) {
//		String query = "select u from User where s.userName = :name";
//		em
//		
//	}

}
