package com.skilldistillery.mvcdesolatemidterm.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.jpadesolatemidterm.entities.Friend;
import com.skilldistillery.jpadesolatemidterm.entities.Game;
import com.skilldistillery.jpadesolatemidterm.entities.PasswordDTO;
import com.skilldistillery.jpadesolatemidterm.entities.Platform;
import com.skilldistillery.jpadesolatemidterm.entities.User;
import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;
import com.skilldistillery.mvcdesolatemidterm.data.GameDAO;
import com.skilldistillery.mvcdesolatemidterm.data.UserDAO;

@Controller
public class GameController {

	@Autowired
	GameDAO gameDao;
	@Autowired
	UserDAO userDao;
	@Autowired
	EventDAO eventDao;

	@RequestMapping(path = "profileView.do")
	public ModelAndView viewProfilePage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PasswordDTO passwordDTO = new PasswordDTO();
		mv.addObject("passwordDTO", passwordDTO);
		List<User> allUsers = gameDao.showAllUsers();
		mv.addObject("allUsers", allUsers);
		User userUpdateGame = (User) session.getAttribute("userCurrent");
		List<Friend> requestList = gameDao.listFriendRequestsForUser(userUpdateGame.getId());
		userUpdateGame = userDao.findUserByUserID(userUpdateGame.getId());
		mv.addObject("requestList", requestList);
		session.setAttribute("userCurrent", userUpdateGame);
		List<User> friendList = gameDao.findUserFriendList(userUpdateGame.getId());
		session.setAttribute("userFriendList", friendList);
		mv.setViewName("WEB-INF/profilePage.jsp");
		return mv;
	}

	@RequestMapping(path = "updateGame.do")
	public ModelAndView viewUpdateGamePage(int id, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Game updateGame = gameDao.findGameById(id);
		mv.addObject("game", updateGame);
		mv.setViewName("WEB-INF/updateGame.jsp");
		return mv;
	}

	@RequestMapping(path = "updateGameInfo.do", method = RequestMethod.POST)
	public ModelAndView updateGamePage(int id, String platform, String title, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User userUpdateGame = (User) session.getAttribute("userCurrent");
		Platform updatePlatform = eventDao.checkPlatformUnique(platform);
		eventDao.createPlatform(updatePlatform);
		Game updatedGame = eventDao.checkGameUnique(title, updatePlatform);
		int userId = userUpdateGame.getId();
		updatedGame = gameDao.updateGame(id, updatedGame, userId);
		userUpdateGame = userDao.findUserByUserID(userUpdateGame.getId());
		session.setAttribute("userCurrent", userUpdateGame);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "addGameToList.do")
	public ModelAndView addGameToUserList(int id, String game, String platform, RedirectAttributes flash,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Game addedGame = gameDao.addUserGame(id, game, platform);
		User updateGamesList = userDao.findUserByUserID(id);
		flash.addFlashAttribute("addedGame", addedGame);
		session.setAttribute("userCurrent", updateGamesList);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "deleteGameFromList.do")
	public ModelAndView deleteGameFromList(int gameId, int userId, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Game removedGame = gameDao.removeGame(gameId, userId);
		flash.addFlashAttribute("removedGame", removedGame);
		User userUpdateGame = userDao.findUserByUserID(userId);
		session.setAttribute("userCurrent", userUpdateGame);
		mv.setViewName("redirect:profileView.do");
		return mv;

	}

	@RequestMapping(path = "viewAllUsers.do")
	public ModelAndView showAllUsers(int id) {
		ModelAndView mv = new ModelAndView();
		List<User> allUsers = gameDao.showAllUsers();
		mv.addObject("allUsers", allUsers);
		mv.setViewName("WEB-INF/allUsers.jsp");
		return mv;
	}

	@RequestMapping(path = "addFriend.do")
	public ModelAndView addNewFriend(int userId, int friendId, RedirectAttributes flash) {
		ModelAndView mv = new ModelAndView();
		User friend = gameDao.addUserToFriendList(userId, friendId);
		flash.addFlashAttribute("friend", friend);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "deleteFriend.do")
	public ModelAndView removeFriendFromList(int userId, int friendId, RedirectAttributes flash) {
		ModelAndView mv = new ModelAndView();
		User friend = gameDao.removeUserFromFriendList(userId, friendId);
		flash.addFlashAttribute("byefriend", friend);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "sendRequest.do")
	public ModelAndView sendFriendRequest(int userId, int friendId, RedirectAttributes flash, String message,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (gameDao.duplicateFriendRequestChecker(userId, friendId)) {
			Friend request = gameDao.sendFriendRequest(userId, message, friendId);
			flash.addFlashAttribute("requestSent", request);

		} else {
			User friend = userDao.findUserByUserID(friendId);
			flash.addFlashAttribute("alreadyFriend", friend);
		}
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "acceptFriendRequest.do")
	public ModelAndView acceptFriendRequest(int requestId, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Friend request = gameDao.findFriendRequest(requestId);
		request = gameDao.acceptFriendRequest(request);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

	@RequestMapping(path = "denyRequest.do")
	public ModelAndView denyFriendRequest(int requestId, RedirectAttributes flash, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Friend request = gameDao.findFriendRequest(requestId);
		gameDao.denyFriendRequest(request);
		mv.setViewName("redirect:profileView.do");
		return mv;
	}

}
