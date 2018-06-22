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
	EventDAOImpl dao = new EventDAOImpl();
	
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
		
		em.persist(platform);
		em.flush();
		return platform;
	}
	
	@Override
	public Game createGame(Game game) {
		
		
		return null;
	}
	
//	@Override
//	public Game checkGameUnique()
	
	@Override
	public Platform checkPlatfromUnique(String platform) {
		String query = "select p from Platfrom p";
		Platform checkPlatform = null;
		List<Platform> platformList = em.createQuery(query, Platform.class).getResultList();
		for (Platform currentPlatform : platformList) {
			if(currentPlatform.getPlatformName() == platform) {
				checkPlatform = currentPlatform;
			}
		}
		if (checkPlatform == null) {
			checkPlatform = new Platform();
			checkPlatform.setPlatformName(platform);
			dao.createPlatform(checkPlatform);
			
		}
		
		return checkPlatform;
	}
	
	
}
