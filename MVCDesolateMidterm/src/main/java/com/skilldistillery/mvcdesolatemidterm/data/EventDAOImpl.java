package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {

	@Autowired
	UserDAO userDao;
	@Autowired
	EventDAO eventDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Event show(int id) {
		return em.find(Event.class, id);
	}

	@Override
	public Event createEvent(String game, String platform, String location, int id) {

		User creator = userDao.findUserByUserID(id);
		Platform eventPlatform = eventDao.checkPlatformUnique(platform);
		eventDao.createPlatform(eventPlatform);
		Game eventGame = eventDao.checkGameUnique(game, eventPlatform);
		eventDao.createGame(eventGame);
		Event createdEvent = new Event();
		createdEvent.setGame(eventGame);
		createdEvent.setLocation(location);
		createdEvent.setCreator(creator);
		createdEvent.setStartDate(new Date());
		createdEvent.setVisibility(1);
		createdEvent.addUser(creator);
		System.out.println(createdEvent.getUsers().get(0));
		
		
		em.persist(createdEvent);
		em.flush();
		return createdEvent;
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

	@Override
	public Event createEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deactivateEvent(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	@Override
//	public boolean deactivateEvent(int id) {
//		Event eventToDeactivate = em.find(Event.class, id);
//		System.out.println("ID: " + id);
//		eventToDeactivate.setStatus(0);
//		em.flush();
//		
//		if(em.find(Event.class, eventToDeactivate.getId()).equals(0)) {
//			System.out.println(eventToDeactivate);
//			return true;
//		}
//		
//		return false;
//	}

}
