package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserTable;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAllUsers") // this will be accessible only if user logs in successfully
	public ResponseEntity<?> getAllUsers()
	{
		List<UserTable> userlist = userService.getAllUsers();
		if(userlist!=null)
		{
			return new ResponseEntity<List<UserTable>>(userlist, HttpStatus.OK);
		}
		return new ResponseEntity<String>("User List is empty", HttpStatus.NO_CONTENT);
	}

}
