package com.demo.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.authentication.entity.UserAuthSuccessHistory;

public interface UserAuthSuccessHistoryRepository extends JpaRepository<UserAuthSuccessHistory, String> {
	
}
