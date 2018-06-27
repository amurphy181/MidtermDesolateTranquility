package com.skilldistillery.mvcdesolatemidterm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.Message;
import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.GameDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;

@Controller
public class EventController {

	@Autowired
	private EventDAO daoEvent;
	@Autowired
	private UserDAO daoUser;
	@Autowired
	private GameDAO daoGame;
	
	@RequestMapping(path="createEvent.do") 
	public ModelAndView createEvent(String game, String platform, String location, int id, HttpSession session) {
		// taking in strings then creating the objects since game requires a platform
		System.out.println("game: " + game + " platform" + platform + " location" + location + " userId" + id);
		ModelAndView mv = new ModelAndView();
		daoEvent.createEvent(game, platform, location, id);
		Game addedGame = daoGame.addUserGame(id, game, platform);
		User updateGamesList = daoUser.findUserByUserID(id);
		session.setAttribute("userCurrent", updateGamesList);
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
	
	@RequestMapping(path="postMessage.do")
	public ModelAndView postMessage(int eventId, String messageContent, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		User user = (User) session.getAttribute("userCurrent");
		System.out.println("user id" + user);
		System.out.println("event id" + eventId);
		int userId = user.getId();
		
		System.out.println(userId);
		Message m = daoEvent.addMessage(messageContent, userId, eventId);
		System.out.println(m);
		mv.setViewName("redirect:landingPage.do");
		return mv;
	}
	
	@RequestMapping(path ="joinEvent.do")
	public ModelAndView joinEvent(int userId, int eventId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User addUserToEvent = daoUser.findUserByUserID(userId);
		Event eventToJoin = daoEvent.findEventByEventID(eventId);
		daoGame.joinEventAddGame(userId, eventToJoin.getGame());
		daoUser.joinEvent(addUserToEvent, eventToJoin);
		addUserToEvent = daoUser.findUserByUserID(userId);
		session.setAttribute("userCurrent", addUserToEvent);
		mv.setViewName("redirect:landingPage.do");
		return mv;
	}
	@RequestMapping(path ="leaveEvent.do")
	public ModelAndView leaveEvent(int userId, int eventId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User removeUserFromEvent = daoUser.findUserByUserID(userId);
		Event eventToLeave = daoEvent.findEventByEventID(eventId);
		daoUser.leaveEvent(removeUserFromEvent, eventToLeave);
		removeUserFromEvent = daoUser.findUserByUserID(userId);
		session.setAttribute("userCurrent", removeUserFromEvent);
		mv.setViewName("redirect:landingPage.do");
		return mv;
	}
	
	
	
	@RequestMapping(path = "deactivateEvent.do", method = RequestMethod.POST)
	public ModelAndView deactivateEvent(HttpSession session, int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("**************" + id);
		daoEvent.deactivateEvent(id);
		session.setAttribute("event", id);

		mv.setViewName("redirect:adminPage.do");
		return mv;
	}
	
	@RequestMapping(path = "reactivateEvent.do", method = RequestMethod.POST)
	public ModelAndView reactivateEvent(HttpSession session, int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("+++++++++++++" + id);
		daoEvent.reactivateEvent(id);
		session.setAttribute("event", id);
		mv.setViewName("redirect:adminPage.do");
		return mv;
	}
	@RequestMapping(path = "userRemoveEvent.do")
	public ModelAndView userRemoveEvent(int userId, int eventId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		daoEvent.deactivateEvent(eventId);
		session.setAttribute("event", eventId);

		mv.setViewName("redirect:landingPage.do");
		return mv;
	}
	
	
	
	
	
	
}
