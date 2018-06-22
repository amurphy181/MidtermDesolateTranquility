package com.skilldistillery.mvcdesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;

public interface EventDAO {

	public Platform checkPlatfromUnique(String platform);
	Event createEvent(Event event);
	Platform createPlatform(Platform platform);

}
