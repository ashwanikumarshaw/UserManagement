package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserTableTest {

	@Test
	void test01() {
		UserTable user= Mockito.mock(UserTable.class);
		String mockusername = "CTS";
		user.setUsername(mockusername);
		when(user.getUsername()).thenReturn(mockusername);
		assertEquals(user.getUsername(),mockusername);
	}
	@Test
	void test02() {
		UserTable user= Mockito.mock(UserTable.class);
		String mockpassworde = "CTS";
		user.setPassword(mockpassworde);
		when(user.getPassword()).thenReturn(mockpassworde);
		assertEquals(user.getPassword(),mockpassworde);
	}
}
