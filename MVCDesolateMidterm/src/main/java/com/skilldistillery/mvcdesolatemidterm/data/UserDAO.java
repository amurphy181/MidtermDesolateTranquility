package com.skilldistillery.mvcdesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.User;

public interface UserDAO {
	
	public User create(User user);
	public User update(int id, User user);
	public boolean passwordConfirmation(User user, String password);
	public User findUserByUsername(String userName);

}
