package com.demo.authentication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.authentication.AuthenticationApplication;
import com.demo.authentication.vo.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationApplication.class)
public class AuthenticationServiceTest {

	@Autowired
	private AuthenticationService authenticationService;
	
	private User user;
	
	@Before
	public void setUpData() {
		user = new User();
		user.userName = ("kumarv@gmail.com");
		user.password = ("Admin@123");
	}
	
	@Test
	public void verifyValidUser() throws Exception {
		assertEquals("000", authenticationService.authenticateUser(user).errorCode);
	}
	
	@Test
	public void verifyInvalidUser() throws Exception {
		user.userName = ("Kumarv@gamil");
		assertEquals("001", authenticationService.authenticateUser(user).errorCode);
	}
	
	@Test
	public void verifyInvalidPassword() throws Exception {
		user.password = ("ADMIN");
		assertEquals("002", authenticationService.authenticateUser(user).errorCode);
	}
	
}
