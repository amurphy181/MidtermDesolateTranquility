package com.skilldistillery.mvcdesolatemidterm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAOImpl;

@Controller
public class EventController {

	@Autowired
	EventDAO dao = new EventDAOImpl();
	
	@RequestMapping(path="createEvent.do")
	public ModelAndView createEvent(String game, String platform, String location) {
		ModelAndView mv = new ModelAndView();
		Platform eventPlatform = dao.checkPlatfromUnique(platform);
		Game eventGame = dao.checkGameUnique(game, eventPlatform);
		Event createdEvent = new Event();
		
		
		
		return mv;
	}
	
	
}
