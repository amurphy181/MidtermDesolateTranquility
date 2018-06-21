package com.skilldistillery.jpadesolatemidterm.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;

	private EntityManager em;

	User user;
	
	@BeforeAll
	public static void setUpAll() throws Exception {
		emf = Persistence.createEntityManagerFactory("DesolateMidterm");
	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
		
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
	void test_user_has_username_and_password() {
		User user = em.find(User.class, 1);
		assertEquals("PurpleFuzz", user.getUserName());
		assertEquals("password", user.getPassword());
	}
	
	@Test
	void test_user_can_add_event() {
		User user = em.find(User.class, 1);
		Event event = new Event();
		event.setGame(em.find(Game.class, 1));
		user.addEvent(event);
		
		assertEquals(1, event.getUsers().size());
		assertEquals("League of Legends", event.getGame().getTitle());
	}
	
}
