package com.demo.authentication.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.authentication.AuthenticationApplication;
import com.demo.authentication.entity.LoginDetailsMaster;
import com.demo.authentication.entity.UsersDetailsMaster;
import com.demo.authentication.repository.UsersDetailsMasterRepository;
import com.demo.authentication.util.Constants;
import com.demo.authentication.vo.CommonVO;
import com.demo.authentication.vo.PINDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationApplication.class)
public class UserMasterRepoTests {

	@Autowired
	private UsersDetailsMasterRepository usersDetailsMasterRepository;
	
	UsersDetailsMaster usersDetailsMaster;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Before
	public void setUpData() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		List<LoginDetailsMaster> loginMasterList = new ArrayList<>();
		LoginDetailsMaster loginMaster;
		PINDetails pinDetails;
		
		usersDetailsMaster = new UsersDetailsMaster();
		String userName = "Kumar";
		String emailId = "kumarv@gmail.com";
		String mobileNo = "9710402255";
		String password = "Admin@123";
		
		CommonVO commonVO = new CommonVO();
		commonVO.createdBy = emailId;
		commonVO.createdOn = currentDate;
		commonVO.modifiedBy = Constants.EMPTY;
		commonVO.modifiedOn = defaultDate;
		
		loginMaster = new LoginDetailsMaster();
		loginMaster.loginId = emailId;
		loginMaster.commonVO = commonVO;
		loginMaster.usersDetailsMaster = usersDetailsMaster;
		loginMasterList.add(loginMaster);
		
		loginMaster = new LoginDetailsMaster();
		loginMaster.loginId = mobileNo;
		loginMaster.commonVO = commonVO;
		loginMaster.usersDetailsMaster = usersDetailsMaster;
		loginMasterList.add(loginMaster);
		
		pinDetails = new PINDetails();
		pinDetails.password = (passwordEncoder.encode(password));
		pinDetails.attemptToIncorrect = (0);
		pinDetails.previousPassword = ("");
		pinDetails.lastUpdatedDate = (currentDate);
		
		usersDetailsMaster.commonVO = commonVO;
		usersDetailsMaster.status = 1;
		usersDetailsMaster.loginDetails = loginMasterList;
		usersDetailsMaster.userName = userName;
		usersDetailsMaster.pinDetails = pinDetails;
		
	}
	
	@Test
	public void registerUserMaster() {
		usersDetailsMasterRepository.save(usersDetailsMaster);
		Assert.assertNotNull(usersDetailsMaster);
	}
	
}
