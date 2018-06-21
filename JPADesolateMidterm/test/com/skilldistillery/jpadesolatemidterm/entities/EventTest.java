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
		event = em.find(Event.class, 1);
		
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
	void test() {
		fail("Not yet implemented");
	}

}
