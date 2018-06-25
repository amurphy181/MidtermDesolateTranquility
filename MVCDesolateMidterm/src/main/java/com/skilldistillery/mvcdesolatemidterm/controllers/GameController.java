package com.skilldistillery.mvcdesolatemidterm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

	@RequestMapping(path="profileView.do")
	public ModelAndView viewProfilePage() {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("WEB-INF/profilePage.jsp");
		return mv;
	}
	
	
}
