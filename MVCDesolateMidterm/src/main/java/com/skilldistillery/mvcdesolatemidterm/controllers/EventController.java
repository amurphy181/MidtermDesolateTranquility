package com.skilldistillery.mvcdesolatemidterm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAOImpl;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAOImpl;

@Controller
public class EventController {

	@Autowired
	EventDAO daoEvent = new EventDAOImpl();
	@Autowired
	UserDAO daoUser = new UserDAOImpl();
	
	@RequestMapping(path="createEvent.do")
	public ModelAndView createEvent(String game, String platform, String location, int userId, Event event) {
		ModelAndView mv = new ModelAndView();
		User creator = daoUser.findUserByUserID(userId);
		Platform eventPlatform = daoEvent.checkPlatfromUnique(platform);
		daoEvent.createPlatform(eventPlatform);
		Game eventGame = daoEvent.checkGameUnique(game, eventPlatform);
		daoEvent.createGame(eventGame);
		Event createdEvent = event;
		createdEvent.setGame(eventGame);
		createdEvent.setLocation(location);
		createdEvent.setCreator(creator);
		daoEvent.createEvent(createdEvent);
		
		mv.setViewName("redirect:landingPage.do");
		
		return mv;
	}
	
	
}
