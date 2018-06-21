package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.data.UserDAOImpl;
import com.skilldistillery.jpadesolatemidterm.entities.User;

public class UserDAOTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		User user = new User();
		User updatedUser = new User();
		UserDAOImpl userDAO = new UserDAOImpl();

		user = em.find(User.class, 2);
		String password = "password";
		
//		user.setUserName("JaredIsSmelly");
//		user.setPassword("giveMeMyPersonalSpace");
		
//		updatedUser.setUserName("bobSuchASnob");
//		updatedUser.setPassword("password123");
		
//		userDAO.create(user); 
//		userDAO.update(4, updatedUser);
		
		boolean confirmed = userDAO.passwordConfirmation(user, password);
		
		if(confirmed) {
			System.out.println("password passes");
		} else {
			System.out.println("incorrect password");
		}
		
		
	}

}
