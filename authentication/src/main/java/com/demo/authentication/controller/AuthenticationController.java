package com.demo.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.authentication.service.AuthenticationService;
import com.demo.authentication.vo.User;
import com.demo.authentication.vo.UserResponse;


/**
 * Created On : 08-May-2021
 * Description : Controller is created for Users Authentication service 
 * @author Kumar V
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * @apiNote To validate the user and generate the token if user is valid
	 */
	@PostMapping(value = "/user/authentication",
			consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public UserResponse authenticate(User user) throws Exception {
		return authenticationService.authenticateUser(user);
	}
	
}
