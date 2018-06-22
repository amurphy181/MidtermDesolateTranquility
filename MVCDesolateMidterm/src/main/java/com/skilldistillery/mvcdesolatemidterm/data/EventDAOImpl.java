package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Event show(int id) {
		return em.find(Event.class, id);
	}

	@Override
	public Event createEvent(Event event) {

		em.persist(event);
		em.flush();
		return event;
	}

	@Override
	public Platform createPlatform(Platform platform) {
		if (em.find(Platform.class, platform.getId()) == null) {
		em.persist(platform);
		em.flush();
		}

		return platform;
	}

	@Override
	public Game createGame(Game game) {
		if (em.find(Game.class, game.getId()) == null) {
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
			if (currentGame.getTitle().equals(game)) {
				checkGame = em.find(Game.class, currentGame.getId());
				checkGame.setPlatform(em.find(Platform.class, platform.getId()));

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
	public Platform checkPlatformUnique(String platform) {
		String query = "select p from Platform p";
		Platform checkPlatform = null;
		List<Platform> platformList = em.createQuery(query, Platform.class).getResultList();
		for (Platform currentPlatform : platformList) {
			if (currentPlatform.getPlatformName().equals(platform)) {
				checkPlatform = em.find(Platform.class, currentPlatform.getId());

			}
		}
		if (checkPlatform == null) {
			checkPlatform = new Platform();
			checkPlatform.setPlatformName(platform);

		}

		return checkPlatform;
	}
	
	@Override
	public Event findEventByEventID(int id) {
		Event found = em.find(Event.class, id);
		
		return found;
	}

}
