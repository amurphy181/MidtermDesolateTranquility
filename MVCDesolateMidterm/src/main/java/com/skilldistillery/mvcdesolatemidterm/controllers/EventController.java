package com.skilldistillery.mvcdesolatemidterm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAOImpl;

@Controller
public class EventController {

	@Autowired
	EventDAO dao = new EventDAOImpl();
	
	@RequestMapping(path="createEvent.do")
	public ModelAndView createEvent() {
		ModelAndView mv = new ModelAndView();
		
		
		
		return mv;
	}
	
	
}
