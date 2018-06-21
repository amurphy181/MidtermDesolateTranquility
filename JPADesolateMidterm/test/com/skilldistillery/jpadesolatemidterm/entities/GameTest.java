package com.skilldistillery.jpadesolatemidterm.entities;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.*;

import com.skilldistillery.jpadesolatemidterm.data.GameDAOImpl;
import com.skilldistillery.jpadesolatemidterm.entities.*;

public class GameTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private static GameDAOImpl dao;
	@BeforeAll
	public static void setUpAll() throws Exception {
		emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		dao = new GameDAOImpl();

	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
	}


	@Test
	void test_game_mappings() {
		Game game = em.find(Game.class, 1);
		assertEquals("League of Legends", game.getTitle());
		assertEquals("PC", game.getPlatform().getPlatformName());
	}
	@Test
	void test_game_to_platform_one_to_many_mappings() {
		Game game = em.find(Game.class, 1);
		assertEquals(5, game.getPlatform().getGames().get(0).getMaxPlayers());
		
	}
	@Test
	void test_game_to_users_many_to_many_mappings() {
		Game game = em.find(Game.class, 1);
		
		User u = new User();
		u.setUserName("abc");
		u.setPassword("boogie");	
		
		Event event = new Event();		
		
		Game newGame = new Game();
		Platform platform = new Platform();
		
		event.setGame(game);
		event.addUser(u);
			
		newGame.addUser(u);
		
		platform.setPlatformName("Test Platform");
		platform.addGame(newGame);
		
		newGame.setMaxPlayers(2);
		newGame.setTitle("Test Title");
		newGame.setPlatform(platform);
		assertEquals("Test Platform", newGame.getPlatform().getPlatformName());
		assertEquals(2, newGame.getMaxPlayers());
		assertEquals("abc", newGame.getUsers().get(0).getUserName());
		assertEquals("League of Legends", event.getGame().getTitle());
		assertEquals("League of Legends", game.getEvents().get(0).getGame().getTitle());
		
	}
//	@Test
//	void test_createActorAndFilm_persist() {
//		Actor a = new Actor();
//				
//		a.setFirstName("Jared");
//		a.setLastName("Groves");
//		
//		Film f = new Film();
//		f.setTitle("BUNGS AND BONGS");
//		f.setRentalRate(2.99);
//		f.setReplacementCost(24.99);
//		
//		Actor actor = dao.createActorAndFilm(a, f);
//		
//		System.out.println(actor);
//		
//		
//		
//		
//		
//		assertEquals("BUNGS AND BONGS", actor.getFilms().get(0).getTitle());
//		System.out.println(dao.destroy(actor.getId()));
//		
//	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
	}
	
	@AfterAll
	public static void tearAllDown() throws Exception{
		emf.close();
		
	}
}

