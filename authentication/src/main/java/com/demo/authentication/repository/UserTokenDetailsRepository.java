package com.demo.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.authentication.entity.UserTokenDetails;

public interface UserTokenDetailsRepository extends JpaRepository<UserTokenDetails, String> {
	
}
