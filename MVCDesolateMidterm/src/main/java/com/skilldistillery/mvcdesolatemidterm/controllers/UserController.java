package com.skilldistillery.mvcdesolatemidterm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(path = "login.do")
	public ModelAndView loginMethod(User user, HttpSession session, Errors error) {
		ModelAndView mv = new ModelAndView();
		if (dao.passwordConfirmation(user, user.getPassword())) {

			if (user != null && error.getErrorCount() == 0) {
				loggedIn = true;
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("user", user);
				mv.setViewName("redirect:landingPage.do");

			}
		} else {
			if (user == null) {
				error.rejectValue("password", "error.password", "error message");
			}
			mv.setViewName("login.jsp");
		}

		return mv;
	}

	@RequestMapping(path = "register.do")
	public ModelAndView registerMethodView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/registerPage.jsp");
		return mv;
	}

	@RequestMapping(path = "landingPage.do")
	public ModelAndView landingPageView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/landingPage.jsp");
		return mv;
	}

}
