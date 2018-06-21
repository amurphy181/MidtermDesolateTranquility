package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.Event;

public class EventClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		Event event = em.find(Event.class, 1);

		System.out.println(event);

		em.close();
		emf.close();
	}
}
