package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jpadesolatemidterm.entities.Friend;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Transactional
@Component
public class GameDAOImpl implements GameDAO {
	
	@Autowired
	private EventDAO eventDao;
	@Autowired
	private GameDAO gameDao;
	
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
		return containGame;
	}
	
	//This long ass method removes the original game from the list of user games. Then the new game to be updated is checked against the database. if the exact game and platform combo exists that game is returned. Otherwise a new game is created and added to the db and user list
	@Override
	public Game updateGame(int id, Game updatedGame, int userId) {
		Game removedFromList = gameDao.findGameById(id);
		Game managed = null;
		User user = em.find(User.class, userId);
		user.removeGame(removedFromList);
		List<Game> allGames = gameDao.findAllGames();
		for (Game game : allGames) {
			if(updatedGame.getPlatform().getPlatformName().equals(game.getPlatform().getPlatformName()) && updatedGame.getTitle().equals(game.getTitle())) {
			
				managed = em.find(Game.class, game.getId());
				user.addGame(managed);
				return managed;
			}
			
		}
		if (managed == null) {
			 managed = updatedGame;
			managed = eventDao.createGame(managed);
			
		}
		
			em.flush();
			user.addGame(managed);

		return managed;
	}
	
	//find specific game by id
	@Override
	public Game findGameById(int id) {
		Game foundGame = em.find(Game.class, id);
		
		return foundGame;
	}
	//finds all games to display or find specific one from list
	@Override
	public List<Game> findAllGames() {
		String query = "select g from Game g";
		List<Game> allGames = em.createQuery(query, Game.class).getResultList();
		
		
		return allGames;
	}
//removes game from user list of games
	@Override
	public Game removeGame(int gameId, int userId) {
		Game removedGame = em.find(Game.class, gameId);
		User user = em.find(User.class, userId);
		user.removeGame(removedGame);
		
		return removedGame;
	}
	
//remember to move all functions below this line to user later
//I want this function to allow users to add friends to their list. I don't want them to add themselves.
//Do I want to view a list of all users to add or a search? lets have this just be a basic add.
	//adds users to friends list
	@Override
	public User addUserToFriendList(int userId, int friendId) {
		User user = em.find(User.class, userId);
		User friend = em.find(User.class, friendId);
		user.addFriend(friend);
		return friend;
	}
	//removes
	@Override
	public User removeUserFromFriendList(int userId, int friendId) {
		User user = em.find(User.class, userId);
		User friend = em.find(User.class, friendId);
		user.removeFriend(friend);
		return friend;
	}
	//shows all for adding users -- want to change to regex later
	@Override
	public List<User> showAllUsers(){
		String query = "select u from User u";
		List<User> allUsers = em.createQuery(query, User.class).getResultList();
		return allUsers;
	}
	
	//attempt to send friend request
	@Override
	public Friend sendFriendRequest(int userId, String message, int friendId) {
		Friend request = new Friend();
		User friend = em.find(User.class, friendId);
		request.setUser(em.find(User.class, userId));
		request.setFriend(friend);
		request.setMessage(message);
		request.setAccepted(false);
		return request;
	}
	@Override
	public Friend acceptFriendRequest(Friend friendRequest) {
		Friend acceptRequest = em.find(Friend.class, friendRequest.getId());
		acceptRequest.setAccepted(true);
		gameDao.addUserToFriendList(acceptRequest.getUser().getId(), acceptRequest.getFriend().getId());
		return acceptRequest;
	}
	@Override
	public Friend findFriendRequest(int requestId) {
		Friend request = em.find(Friend.class, requestId);
		
		return request;
	}
	
}
