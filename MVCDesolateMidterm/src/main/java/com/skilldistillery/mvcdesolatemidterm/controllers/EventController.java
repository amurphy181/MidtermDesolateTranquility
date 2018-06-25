package com.skilldistillery.mvcdesolatemidterm.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	private EventDAO daoEvent;
	@Autowired
	private UserDAO daoUser;
	
	@RequestMapping(path="createEvent.do") 
	public ModelAndView createEvent(String game, String platform, String location, int id) {
		// taking in strings then creating the objects since game requires a platform
		System.out.println("game: " + game + " platform" + platform + " location" + location + " userId" + id);
		ModelAndView mv = new ModelAndView();
		daoEvent.createEvent(game, platform, location, id);
		
		mv.setViewName("redirect:landingPage.do");
		
		return mv;
	}
	
	@RequestMapping(path = "getEventId.do", method = RequestMethod.GET)
	public ModelAndView getJobApp(@RequestParam("fid") int fid) {
		ModelAndView mv = new ModelAndView();

		Event eventLink = daoEvent.show(fid);

		mv.addObject("event", eventLink);
		mv.setViewName("WEB-INF/adminPage.jsp");
		return mv;
	}
	
	@RequestMapping(path ="joinEvent.do")
	public ModelAndView joinEvent(int userId, int eventId) {
		ModelAndView mv = new ModelAndView();
		User addUserToEvent = daoUser.findUserByUserID(userId);
		Event eventToJoin = daoEvent.findEventByEventID(eventId);
		daoUser.joinEvent(addUserToEvent, eventToJoin);
		mv.setViewName("redirect:landingPage.do");
		return mv;
	}
	
	@RequestMapping(path = "deactivateEvent.do", method = RequestMethod.POST)
	public ModelAndView deactivateEvent(int id) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("**************" + id);

		daoEvent.deactivateEvent(id);

		mv.setViewName("redirect:adminPage.jsp");

		return mv;
	}
	
	@RequestMapping(path = "reactivateEvent.do", method = RequestMethod.POST)
	public ModelAndView reactivateEvent(int id) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("+++++++++++++" + id);
		
		daoEvent.reactivateEvent(id);
		
		mv.setViewName("redirect:adminPage.jsp");
		
		return mv;
	}
	
	
	
}
