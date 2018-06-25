package com.skilldistillery.mvcdesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.Game;

public interface GameDAO {

	Game addUserGame(int id, String game, String platform);

	boolean joinEventAddGame(int id, Game game);

	Game updateGame(int id, Game updatedGame);

	Game findGameById(int id);

}
