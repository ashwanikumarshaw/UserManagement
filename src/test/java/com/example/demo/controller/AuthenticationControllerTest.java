package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.example.demo.model.UserTable;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class AuthenticationControllerTest {

	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private AuthenticationController authenticationController;

	@Autowired
	private MockMvc mockmvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
	}

	private List<UserTable> userlist = new ArrayList();

	@Test
	public void addUserSuccess() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("root");
		user.setPassword("pass");

		userlist.add(user);
		when(userService.addUser(any())).thenReturn(user);

		UserTable user1 = userService.addUser(user);

		assertEquals(user, user1);

		mockmvc.perform(MockMvcRequestBuilders.post("/auth/user/addUser").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void addUserFailure() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("root");
		user.setPassword("pass");

		userlist.add(user);
		when(userService.addUser(any())).thenReturn(null);

		UserTable user1 = userService.addUser(user);

		assertNull(user1);

		mockmvc.perform(MockMvcRequestBuilders.post("/auth/user/addUser").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());

	}

	@Test
	public void doLoginSuccess() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("root");
		user.setPassword("pass");

		userlist.add(user);
		when(userService.addUser(any())).thenReturn(user);

		UserTable user1 = userService.addUser(user);

		assertEquals(user, user1);

		mockmvc.perform(MockMvcRequestBuilders.post("/auth/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void doLoginFailure() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("root");
		user.setPassword("pass");

		userlist.add(user);
		when(userService.addUser(any())).thenReturn(null);

		UserTable user1 = userService.addUser(user);

		assertNull(user1);

		mockmvc.perform(MockMvcRequestBuilders.post("/auth/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isOk());

	}
}
