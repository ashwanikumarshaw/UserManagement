package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserTable;
import com.example.demo.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("auth/user")
public class AuthenticationController 
{
	private Map<String, String> mapObj = new HashMap<String, String>();
	
	@Autowired
	private UserServiceImpl userImpl;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserTable user)
	{if(userImpl.addUser(user)!=null)
	{
		return new ResponseEntity<UserTable>(user, HttpStatus.CREATED);
	}
	return new ResponseEntity<String>("Data not inserted", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	public String generateToken(String username, String password) throws ServletException,Exception
	{
		String jwtToken="";
		
		if(username==null || password == null)
		{
			throw new ServletException("Please enter valid username and password");
		}
		
		boolean flag = userImpl.validateUser(username, password);
		
		if(!flag)
		{
			throw new ServletException("Invalid credentials");
		}
		else
		{
			jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+6000000))
					.signWith(SignatureAlgorithm.HS256, "my secret key")
					.compact();
		}
		return jwtToken;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody UserTable user)
	{
		try
		{
			String getToken = generateToken(user.getUsername(), user.getPassword());
			mapObj.put("message", "User logged in successfully");
			mapObj.put("Token", getToken);
		}
		catch(Exception e)
		{
			mapObj.put("message", "User not logged in successfully");
			mapObj.put("Token", null);
		}
		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}

}















