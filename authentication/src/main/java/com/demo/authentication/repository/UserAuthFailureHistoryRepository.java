package com.demo.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.authentication.entity.UserAuthFailureHistory;

public interface UserAuthFailureHistoryRepository extends JpaRepository<UserAuthFailureHistory, String> {
	
}
