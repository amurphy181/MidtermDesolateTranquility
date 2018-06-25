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
	public List<Event> listAllEvents();
	public User findUserByUserID(int id);
	boolean joinEvent(User user, Event event);
	public List<User> listAllUsers();
	public boolean deactivateUser(int id);
	public boolean reactivateUser(int id);

}
