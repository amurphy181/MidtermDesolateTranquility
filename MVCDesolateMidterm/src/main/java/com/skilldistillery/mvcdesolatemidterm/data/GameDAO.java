package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import com.skilldistillery.jpadesolatemidterm.entities.Game;

public interface GameDAO {

	Game addUserGame(int id, String game, String platform);

	boolean joinEventAddGame(int id, Game game);

	Game findGameById(int id);

	List<Game> findAllGames();

	Game updateGame(int id, Game updatedGame, int userId);

}
