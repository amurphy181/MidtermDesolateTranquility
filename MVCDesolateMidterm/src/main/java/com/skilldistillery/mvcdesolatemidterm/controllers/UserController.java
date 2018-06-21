package com.skilldistillery.mvcdesolatemidterm.controllers;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAOImpl;

@Controller
public class UserController {

	@Autowired
	UserDAO dao = new UserDAOImpl();
	private boolean loggedIn;

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
		System.out.println(user.getPassword());
		if (userLogin != null) {
			if (dao.passwordConfirmation(userLogin, user.getPassword())) {

				loggedIn = true;
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("user", user);
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

	@RequestMapping(path = "register.do")
	public ModelAndView registerMethodView() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("WEB-INF/registerView.jsp");
		return mv;
	}

	@RequestMapping(path = "landingPage.do")
	public ModelAndView landingPageView() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("WEB-INF/landingPage.jsp");
		return mv;
	}
	@RequestMapping(path = "registration.do", method = RequestMethod.POST)
	public ModelAndView Registered(User user, Errors error, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (dao.uniqueUsername(user.getUserName())) {
		//	mv.addObject("user", user);
			session.setAttribute("user", user);
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

}
