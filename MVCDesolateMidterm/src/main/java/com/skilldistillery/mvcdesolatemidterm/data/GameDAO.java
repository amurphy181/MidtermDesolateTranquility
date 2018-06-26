package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.User;

public interface GameDAO {

	Game addUserGame(int id, String game, String platform);

	boolean joinEventAddGame(int id, Game game);

	Game findGameById(int id);

	List<Game> findAllGames();

	Game updateGame(int id, Game updatedGame, int userId);


	Game removeGame(int gameId, int userId);

	User addUserToFriendList(int userId, int friendId);

	User removeUserFromFriendList(int userId, int friendId);

	List<User> showAllUsers();

}
