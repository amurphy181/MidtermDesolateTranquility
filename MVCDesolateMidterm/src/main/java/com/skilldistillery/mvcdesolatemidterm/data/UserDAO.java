package com.skilldistillery.mvcdesolatemidterm.data;

import java.util.List;

import com.skilldistillery.jpadesolatemidterm.entities.Event;
import com.skilldistillery.jpadesolatemidterm.entities.User;

public interface UserDAO {
	
	public User create(User user);
	public User update(int id, User user);
	public boolean passwordConfirmation(User user, String password);
	public User findUserByUsername(String userName);
	boolean uniqueUsername(String userName);
	List<Event> listAllEvents();
	User findUserByUserID(int id);

}
