package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserTable;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<UserTable> getAllUsers() {
		List<UserTable> userlist = userRepo.findAll();
		if (userlist != null && userlist.size() > 0) {
			return userlist;
		} else
			return null;
	}

	@Override
	public UserTable addUser(UserTable user) {
		UserTable u = userRepo.save(user);

		if (u != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean validateUser(String username, String password) {
		UserTable user = userRepo.validateUser(username, password);
		if (user != null) {
			return true;
		} else
			return false;
	}

}
