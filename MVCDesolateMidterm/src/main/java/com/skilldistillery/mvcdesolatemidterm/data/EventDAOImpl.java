package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Event createEvent(Event event) {

		em.persist(event);
		em.flush();
		return event;
	}
	
	@Override
	public Platform createPlatform(Platform platform) {
		
		if(em.find(Platform.class, platform.getId()) == null) {
			em.persist(platform);
			em.flush();
			
		}
		
		return platform;
	}
	
	@Override
	public Game createGame(Game game) {
		if(em.find(Game.class, game.getId()) == null) {
			em.persist(game);
			em.flush();
			
		}
		return game;
	}
	
	@Override
	public Game checkGameUnique(String game, Platform platform) {
		String query = "select g from Game g";
		Game checkGame = null;
		List<Game> gameList = em.createQuery(query, Game.class).getResultList();
		for (Game currentGame : gameList) {
			if(currentGame.getTitle().equals(game)) {
				checkGame = currentGame;
				checkGame.setPlatform(platform);
			}
		}
		if (checkGame == null) {
			checkGame = new Game();
			checkGame.setTitle(game);
			checkGame.setPlatform(platform);
			
		}
		
		
		return checkGame;

	}
	
	@Override
	public Platform checkPlatfromUnique(String platform) {
		String query = "select p from Platfrom p";
		Platform checkPlatform = null;
		List<Platform> platformList = em.createQuery(query, Platform.class).getResultList();
		for (Platform currentPlatform : platformList) {
			if(currentPlatform.getPlatformName().equals(platform)) {
				checkPlatform = currentPlatform;
			}
		}
		if (checkPlatform == null) {
			checkPlatform = new Platform();
			checkPlatform.setPlatformName(platform);
			
		}
		
		return checkPlatform;
	}
	
	
}
