package com.demo.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.authentication.entity.LoginDetailsMaster;

public interface LoginDetailsRepository extends JpaRepository<LoginDetailsMaster, String>{
	
	public Optional<LoginDetailsMaster> findByLoginId(String loginId);
	public boolean existsByLoginId(String loginId);
	
}
