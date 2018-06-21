package com.skilldistillery.jpadesolatemidterm.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
	
	private static EntityManagerFactory emf;

	private EntityManager em;

	Event event;
	
	@BeforeAll
	public static void setUpAll() throws Exception {
		emf = Persistence.createEntityManagerFactory("DesolateMidterm");
	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
//		event = em.find(Event.class, 1);
		
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		
	}

	@AfterAll
	public static void tearDownAll() throws Exception {
		emf.close();
	}
	
	@Test
	void test_event_has_users_and_game() {
		Event event = new Event();
		User user = em.find(User.class, 1);
		User user2 = em.find(User.class, 2);
		Game game = em.find(Game.class, 1);
		user.addEvent(event);
		user.addGame(game);
		event.addUser(user);
		event.addUser(user2);
		event.setGame(game);
		assertEquals(2, event.getUsers().size());
		assertEquals("League of Legends", event.getGame().getTitle());
	}
	
	@Test
	void test_event_has_location() {
		Event event = new Event();
		User user = em.find(User.class, 1);
		User user2 = em.find(User.class, 2);
		Game game = em.find(Game.class, 1);
		user.addEvent(event);
		user.addGame(game);
		event.addUser(user);
		event.addUser(user2);
		event.setGame(game);
		event.setLocation("right here");
		
		assertEquals("right here", event.getLocation());
	}
	
	@Test
	void test_event_has_visibility() {
		Event event = new Event();
		User user = em.find(User.class, 1);
		User user2 = em.find(User.class, 2);
		Game game = em.find(Game.class, 1);
		user.addEvent(event);
		user.addGame(game);
		event.addUser(user);
		event.addUser(user2);
		event.setGame(game);
		event.setVisibility(1);
		
		assertEquals(1, event.getVisibility());
	}

}
