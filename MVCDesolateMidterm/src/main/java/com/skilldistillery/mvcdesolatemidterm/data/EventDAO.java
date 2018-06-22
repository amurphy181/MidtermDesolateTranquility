package com.skilldistillery.mvcdesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;

public interface EventDAO {

	public Platform checkPlatfromUnique(String platform);
	public Event createEvent(Event event);
	public Platform createPlatform(Platform platform);
	public Game createGame(Game game);

}
