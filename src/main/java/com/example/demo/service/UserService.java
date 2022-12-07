package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserTable;

public interface UserService 
{
	public List<UserTable> getAllUsers();
	
	public UserTable addUser(UserTable user);
	
	public boolean validateUser(String username, String password);
	
	

}
