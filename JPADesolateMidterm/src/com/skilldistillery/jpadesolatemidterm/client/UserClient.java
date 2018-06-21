package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.User;


public class UserClient {
	
	public static void main(String[] args) {

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, 1);

		System.out.println(user);

		em.close();
		emf.close();
		
		
	}
}
