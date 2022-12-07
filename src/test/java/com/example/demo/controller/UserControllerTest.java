package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.model.UserTable;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockmvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	private List<UserTable> userlist = new ArrayList();

	@Test
	public void getAllUserSuccess() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("Keith");
		user.setPassword("abcd");

		userlist.add(user);
		when(userService.getAllUsers()).thenReturn(userlist);

		List<UserTable> uList = userService.getAllUsers();

		assertEquals(userlist, uList);

		mockmvc.perform(MockMvcRequestBuilders.get("/api/user/getAllUsers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void getAllUserFailure() throws Exception {

		when(userService.getAllUsers()).thenReturn(null);

		assertEquals(0, userlist.size());

		mockmvc.perform(MockMvcRequestBuilders.get("/api/user/getAllUsers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}

	
}
