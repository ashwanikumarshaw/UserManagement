package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.UserTable;
import com.example.demo.repository.UserRepository;

class UserServiceTest {

	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@Autowired
	private MockMvc mockmvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(userService).build();
	}

	private List<UserTable> userlist = new ArrayList();

	@Test
	public void getAllUserSuccess() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("Keith");
		user.setPassword("abcd");

		userlist.add(user);
		when(userRepo.findAll()).thenReturn(userlist);

		List<UserTable> uList = userService.getAllUsers();

		assertEquals(userlist, uList);

	}

	@Test
	public void getAllUserFailure() throws Exception {

		when(userRepo.findAll()).thenReturn(null);

		List<UserTable> uList = userService.getAllUsers();

		assertNull(uList);

	}

	@Test
	public void addUserSuccess() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("Keith");
		user.setPassword("abcd");

		userlist.add(user);
		when(userRepo.save(any())).thenReturn(user);

		UserTable user1 = userService.addUser(user);

		assertEquals(user, user1);
	}

	@Test
	public void addUserFailure() throws Exception {
		UserTable user = new UserTable();

		user.setId(101);
		user.setUsername("Keith");
		user.setPassword("abcd");

		userlist.add(user);
		when(userRepo.save(any())).thenReturn(null);

		UserTable user1 = userService.addUser(user);

		assertNull(user1);
	}

}
