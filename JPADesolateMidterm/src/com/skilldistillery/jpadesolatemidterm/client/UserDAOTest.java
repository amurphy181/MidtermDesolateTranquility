package com.skilldistillery.jpadesolatemidterm.client;

import com.skilldistillery.jpadesolatemidterm.data.UserDAOImpl;
import com.skilldistillery.jpadesolatemidterm.entities.User;

public class UserDAOTest {

	public static void main(String[] args) {
		User user = new User();
		User updatedUser = new User();
		UserDAOImpl userDAO = new UserDAOImpl();

		
		
		user.setUserName("JaredIsSmelly");
		user.setPassword("giveMeMyPersonalSpace");
//		updatedUser.setUserName("bobSuchASnob");
//		updatedUser.setPassword("password123");
		
		userDAO.create(user); 
//		userDAO.update(4, updatedUser);
		
		
		System.out.println(user);
		
	}

}
