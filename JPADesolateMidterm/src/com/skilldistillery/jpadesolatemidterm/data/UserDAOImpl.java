package com.skilldistillery.jpadesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User create(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();

		// start the transaction
		em.getTransaction().begin();
		// write the customer to the database
		em.persist(user);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();
//		em.close();
		emf.close();
		// return the Customer object
		return user;
	}

	@Override
	public User update(int id, User updatedUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		User managed = em.find(User.class, id);
//		managed.setFirstName(updatedUser.getFirstName());
//		managed.setLastName(updatedUser.getLastName());

		em.flush();
		em.getTransaction().commit();

//		em.close();
		emf.close();
		return managed;
	}

}
