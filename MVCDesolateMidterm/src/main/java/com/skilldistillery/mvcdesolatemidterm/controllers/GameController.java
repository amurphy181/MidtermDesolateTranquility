package com.skilldistillery.mvcdesolatemidterm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.GameDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;

@Controller
public class GameController {

	@Autowired
	GameDAO gameDao;
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(path="profileView.do")
	public ModelAndView viewProfilePage() {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("WEB-INF/profilePage.jsp");
		return mv;
	}
	@RequestMapping(path="addGameToList.do")
	public ModelAndView addGameToUserList(int id, String game, String platform, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Game addedGame = gameDao.addUserGame(id, game, platform);
		User updateGamesList = userDao.findUserByUserID(id);
		flash.addFlashAttribute("addedGame", addedGame.getTitle());
		System.out.println(addedGame.getTitle());
		session.setAttribute("userCurrent", updateGamesList);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}
	
}
