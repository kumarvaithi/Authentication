package com.demo.authentication.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.authentication.entity.LoginDetailsMaster;
import com.demo.authentication.entity.UserAuthFailureHistory;
import com.demo.authentication.entity.UserAuthSuccessHistory;
import com.demo.authentication.entity.UserTokenDetails;
import com.demo.authentication.entity.UsersDetailsMaster;
import com.demo.authentication.repository.LoginDetailsRepository;
import com.demo.authentication.repository.UserAuthFailureHistoryRepository;
import com.demo.authentication.repository.UserAuthSuccessHistoryRepository;
import com.demo.authentication.repository.UserTokenDetailsRepository;
import com.demo.authentication.util.Constants;
import com.demo.authentication.vo.CommonVO;
import com.demo.authentication.vo.User;
import com.demo.authentication.vo.UserResponse;


@Service
@Transactional
public class AuthenticationService {
	
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageByLocaleService localResolver;
	
	@Autowired
	private UserTokenDetailsRepository userTokenDetailsRepository;
	
	@Autowired
	private UserAuthSuccessHistoryRepository userAuthSuccessHistoryRepository; 
	
	@Autowired
	private UserAuthFailureHistoryRepository userAuthFailureHistoryRepository;
	
	
	public UserResponse authenticateUser(User request) throws Exception {
		UserResponse userResponse = new UserResponse();
		
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		Optional<LoginDetailsMaster> loginDetails = loginDetailsRepository.findByLoginId(request.userName);
		
		if(loginDetails.isPresent()) {
			LoginDetailsMaster login = loginDetails.get();
			String encodedPassword = login.usersDetailsMaster.pinDetails.password;
			
			if(passwordEncoder.matches(request.password, encodedPassword)) {
				
				CommonVO commonVO = new CommonVO();
				commonVO.createdBy = request.userName;
				commonVO.createdOn = currentDate;
				commonVO.modifiedBy = Constants.EMPTY;
				commonVO.modifiedOn = defaultDate;
				
				
				// User is valid generate and store token in database
				UserTokenDetails userAuthDetails = new UserTokenDetails();
				userAuthDetails.status = 1;
				userAuthDetails.systemId = login.usersDetailsMaster.systemId;
				userAuthDetails.commonVO = commonVO;
				userTokenDetailsRepository.save(userAuthDetails);
				
				
				// User is valid and put success entry
				UserAuthSuccessHistory userLoginSuccessHistory = new UserAuthSuccessHistory();
				userLoginSuccessHistory.loginDate = (currentDate);
				userLoginSuccessHistory.systemId = login.usersDetailsMaster.systemId;
				userLoginSuccessHistory.userName = (login.usersDetailsMaster.userName);
				userAuthSuccessHistoryRepository.save(userLoginSuccessHistory);
				
				
				//Success Response
				userResponse.errorCode = Constants.SUCCESS;
				userResponse.errorDesc = localResolver.getErrorMessage(Constants.SUCCESS);
				userResponse.token = userAuthDetails.token;
			} else {
				// Valid user but Password is incorrect put entry in Database
				UsersDetailsMaster userMaster = login.usersDetailsMaster;
				userMaster.pinDetails.attemptToIncorrect = (userMaster.pinDetails.attemptToIncorrect + 1);
				
				UserAuthFailureHistory userLoginFailureHistory = new UserAuthFailureHistory();
				userLoginFailureHistory.loginDate = currentDate;
				userLoginFailureHistory.systemId = userMaster.systemId;
				userLoginFailureHistory.errorCode = Constants.INVALID_PASSWORD;
				userLoginFailureHistory.errorDesc = localResolver.getErrorMessage(Constants.INVALID_PASSWORD);
				userLoginFailureHistory.userName = userMaster.userName;
				userAuthFailureHistoryRepository.save(userLoginFailureHistory);
				
				userResponse.errorCode = (Constants.INVALID_PASSWORD);
				userResponse.errorDesc = (localResolver.getErrorMessage(Constants.INVALID_PASSWORD));
				userResponse.token = "";
			}
		} else { // else User is not available in Database
			UserAuthFailureHistory userLoginFailureHistory = new UserAuthFailureHistory();
			userLoginFailureHistory.loginDate = currentDate;
			userLoginFailureHistory.systemId = "";
			userLoginFailureHistory.errorCode = Constants.INVALID_USER;
			userLoginFailureHistory.errorDesc = localResolver.getErrorMessage(Constants.INVALID_USER);
			userLoginFailureHistory.userName = request.userName;
			userAuthFailureHistoryRepository.save(userLoginFailureHistory);
			
			userResponse.errorCode = Constants.INVALID_USER;
			userResponse.errorDesc = localResolver.getErrorMessage(Constants.INVALID_USER);
			userResponse.token = "";
		}
		return userResponse;
	}
	
}
