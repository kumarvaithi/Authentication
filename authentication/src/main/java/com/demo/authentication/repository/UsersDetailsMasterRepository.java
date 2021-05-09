package com.demo.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.authentication.entity.UsersDetailsMaster;

@Repository
public interface UsersDetailsMasterRepository extends JpaRepository<UsersDetailsMaster, String> {
	
	
}
