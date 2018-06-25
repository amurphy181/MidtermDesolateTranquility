package com.skilldistillery.mvcdesolatemidterm.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAOImpl;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAOImpl;

@Controller
public class UserController {

	@Autowired
	private UserDAO dao;
	@Autowired
	private EventDAO eventDAO;

	// you have the session to check for this
	private boolean loggedIn;

	// user controllers follow

	@RequestMapping(path = "welcome.do")
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("WEB-INF/loginView.jsp");

		return mv;
	}

	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public ModelAndView loginMethod(User user, HttpSession session, Errors error) {
		ModelAndView mv = new ModelAndView();
		User userLogin = dao.findUserByUsername(user.getUserName());
		System.out.println(userLogin);
		System.out.println(user.getPassword());
		if (userLogin != null) {
			if (dao.passwordConfirmation(userLogin, user.getPassword())) {

				loggedIn = true;
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("userCurrent", userLogin);
				List<Event> eventList = dao.listAllEvents();
				session.setAttribute("events", eventList);
				mv.setViewName("WEB-INF/landingPage.jsp");
			} else {
				error.rejectValue("password", "error.password", "error message");
				mv.setViewName("WEB-INF/loginView.jsp");

			}

		} else {

			error.rejectValue("userName", "error.userName", "error message");
			mv.setViewName("WEB-INF/loginView.jsp");
		}

		return mv;
	}
	
	@RequestMapping(path = "logout.do", method = RequestMethod.GET)
	public ModelAndView logoutMethod(@ModelAttribute("userCurrent") User user, HttpSession session, RedirectAttributes flash) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println(user);
		User userLogout = (User) session.getAttribute("userCurrent");
		System.out.println(userLogout);
		System.out.println("!@!@!@!@ LOGOUT TESTER @!@!@!@!");
		
		if(userLogout != null) {
			loggedIn = false;
			session.invalidate();
			flash.addFlashAttribute("logOut", userLogout.getUserName());
			mv.setViewName("redirect:welcome.do");
		} else {
			System.out.println("LOGOUT failed.\n");
		}
		
		
		return mv;
	}
	
	
	

	@RequestMapping(path = "register.do")
	public ModelAndView registerMethodView() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("WEB-INF/registerView.jsp");
		return mv;
	}

	@RequestMapping(path = "adminPage.do")
	public ModelAndView adminPageView(HttpSession session, User user) {
		ModelAndView mv = new ModelAndView();
		List<User> completeUserList = dao.listAllUsers();
		List<Event> completeEventList = dao.listAllEvents();
		mv.addObject("events", completeEventList);
		mv.addObject("completeUserList", completeUserList);
		System.out.println(completeUserList);
		mv.setViewName("WEB-INF/adminPage.jsp");

		return mv;
	}

	@RequestMapping(path = "landingPage.do")
	public ModelAndView landingPageView(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<Event> eventList = dao.listAllEvents();

		session.setAttribute("events", eventList);
		User user = (User) session.getAttribute("user");
		if (eventList == null) {
			System.out.println("event list is null");
		} else {
			System.out.println("it went on through");
		}
		System.out.println(eventList);
		session.setAttribute("user", user);
		mv.setViewName("WEB-INF/landingPage.jsp");
		return mv;
	}

	@RequestMapping(path = "registration.do", method = RequestMethod.POST)
	public ModelAndView Registered(User user, Errors error, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (dao.uniqueUsername(user.getUserName())) {
			// mv.addObject("user", user);
			dao.create(user);

			boolean added = true;

			flash.addFlashAttribute("added", added);
			mv.setViewName("redirect:welcome.do");

		} else {
			error.rejectValue("userName", "error.userName", "error message");
			mv.setViewName("WEB-INF/registerView.jsp");
		}
		return mv;
	}

	@RequestMapping(path = "deactivateUser.do", method = RequestMethod.POST)
	public ModelAndView deactivateUser(HttpSession session, int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("**************" + id);
		dao.deactivateUser(id);
		List<User> completeUserList = dao.listAllUsers();
		mv.addObject("completeUserList", completeUserList);
		session.setAttribute("user", id);
		mv.setViewName("redirect:adminPage.do");
		return mv;

	}

	@RequestMapping(path = "reactivateUser.do", method = RequestMethod.POST)
	public ModelAndView reactivateUser(HttpSession session, int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("**************" + id);
		dao.reactivateUser(id);
		List<User> completeUserList = dao.listAllUsers();
		mv.addObject("completeUserList", completeUserList);
		session.setAttribute("user", id);
		mv.setViewName("redirect:adminPage.do");
		return mv;

	}

}
