package com.skilldistillery.mvcdesolatemidterm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpadesolatemidterm.data.UserDAO;
import com.skilldistillery.jpadesolatemidterm.data.UserDAOImpl;
import com.skilldistillery.jpadesolatemidterm.entities.User;

@Controller
public class UserController {

	@Autowired
	UserDAO dao = new UserDAOImpl(); 
	private boolean loggedIn;

	
	@RequestMapping(path="welcome.do")
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/loginView.jsp");
		
		return mv;
	}
	
	@RequestMapping(path="login.do")
	public ModelAndView loginMethod(String userName, String password, HttpSession session, Errors error) {
		ModelAndView mv = new ModelAndView();
		dao.
		if (dao.passwordConfirmation(user, password)) {
			mv.setViewName("WEB-INF/landingPage.jsp");		
			User u = new User();
		}
		if (u != null && error.getErrorCount() == 0) {
			loggedIn = true;
			session.setAttribute("loggedIn", loggedIn);
			session.setAttribute("user", u);
			mv.setViewName("redirect:account.do");

		} else {
			if (u == null) {
				error.rejectValue("password", "error.password", "error message");
			}
			mv.setViewName("login.jsp");
		}
		
		return mv;
	}
	@RequestMapping(path="register.do")
	public ModelAndView registerMethodView() {
		ModelAndView mv = new ModelAndView();			
		mv.setViewName("WEB-INF/registerPage.jsp");
		return mv;
	}
	
}
