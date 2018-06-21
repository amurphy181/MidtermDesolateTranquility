package com.skilldistillery.jpadesolatemidterm.data;

import com.skilldistillery.jpadesolatemidterm.entities.User;

public interface UserDAO {
	
	public User create(User user);
	public User update(int id, User user);
	public boolean passwordConfirmation(User user, String password);

}
