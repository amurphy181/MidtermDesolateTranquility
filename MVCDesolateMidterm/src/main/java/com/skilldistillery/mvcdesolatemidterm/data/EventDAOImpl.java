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
import com.skilldistillery.jpadesolatemidterm.entities.Message;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {

	@Autowired
	UserDAO userDao;
	@Autowired
	EventDAO eventDao;
	@Autowired
	GameDAO gameDao;

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
		gameDao.addUserGame(id, game, platform);

		Event createdEvent = new Event();
		createdEvent.setGame(eventGame);
		createdEvent.setLocation(location);
		createdEvent.setCreator(creator);
		createdEvent.setStartDate(new Date());
		createdEvent.setVisibility(1);
		createdEvent.addUser(creator);
		createdEvent.setStatus(true);

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
			if (currentGame.getTitle().equals(game) && currentGame.getPlatform().equals(platform)) {
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
	public boolean deactivateEvent(int id) {
		Event eventToDeactivate = em.find(Event.class, id);
		eventToDeactivate.setStatus(false);
		em.flush();

		if (em.find(Event.class, eventToDeactivate.getId()).equals(false)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean reactivateEvent(int id) {
		Event eventToReactivate = em.find(Event.class, id);
		eventToReactivate.setStatus(true);
		Date startDate = new Date();
		eventToReactivate.setStartDate(startDate);
		em.flush();

		if (em.find(Event.class, eventToReactivate.getId()).equals(true)) {
			return true;
		}
		return false;
	}

	@Override
	public Message addMessage(String messageContent, int userId, int eventId) {
		Event eventToAddMessageTo = em.find(Event.class, eventId);

		Message m = new Message();
		m.setContent(messageContent);
		m.setEvent(eventToAddMessageTo);
		m.setUser(em.find(User.class, userId));


		em.persist(m);
		em.flush();

		return m;
	}

}
