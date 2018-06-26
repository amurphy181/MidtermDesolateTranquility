package com.skilldistillery.mvcdesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Message;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;

public interface EventDAO {

	public Platform checkPlatformUnique(String platform);
	public Platform createPlatform(Platform platform);
	public Game checkGameUnique(String game, Platform platform);
	public Game createGame(Game game);
	public Event show(int id);
	public Event findEventByEventID(int id);
	public boolean deactivateEvent(int id);
	public Event createEvent(String game, String platform, String location, int id);
	public boolean reactivateEvent(int id);
	public Message addMessage(String messageContent, int userId, int eventId);

}
