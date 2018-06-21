package com.skilldistillery.jpadesolatemidterm.client;

import com.skilldistillery.jpadesolatemidterm.data.UserDAOImpl;
import com.skilldistillery.jpadesolatemidterm.entities.User;

public class UserDAOTest {

	public static void main(String[] args) {
		User user = new User();
		UserDAOImpl userDAO = new UserDAOImpl();

		user.setUserName("bobTheSnob");
		user.setPassword("password");
		
		userDAO.create(user);
		System.out.println(user);
		
	}

}
