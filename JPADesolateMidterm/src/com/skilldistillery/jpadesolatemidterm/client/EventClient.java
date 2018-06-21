package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.*;

public class EventClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		Event event = em.find(Event.class, 1);

		event.addUser(em.find(User.class, 2));
		System.out.println(event);
		event.removeUser(event.getUsers().get(0));

		em.close();
		emf.close();
	}
}
