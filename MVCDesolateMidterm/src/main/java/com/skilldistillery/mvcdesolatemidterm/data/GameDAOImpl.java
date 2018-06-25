package com.skilldistillery.mvcdesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class GameDAOImpl implements GameDAO {
	
	@Autowired
	private EventDAO eventDao;
	
	@PersistenceContext
	private EntityManager em;
	
	
	//Users have a list of games, this allows users to add to the list.
	@Override
	public Game addUserGame(int id, String game, String platform) {
		User userAddGame = em.find(User.class, id);
		Platform platformAddGame = eventDao.checkPlatformUnique(platform);
		eventDao.createPlatform(platformAddGame);
		Game addedGame = eventDao.checkGameUnique(game, platformAddGame);
		addedGame.setVisible(true);
		eventDao.createGame(addedGame);
		userAddGame.addGame(addedGame);
		return addedGame;
		
	}
	
	//If a user joins an event and the game is not in their library it will automatically be added.
	@Override
	public boolean joinEventAddGame(int id, Game game) {
		Game newGame = em.find(Game.class, game.getId());
		boolean containGame = false;
		User joinEventUser = em.find(User.class, id);
			joinEventUser.addGame(newGame);
			System.out.println("============================="+joinEventUser.getGames());
		return containGame;
	}

}
