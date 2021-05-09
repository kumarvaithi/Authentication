package com.demo.authentication.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthFailureHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(length = 30, nullable = false)
	public String systemId;

	@Column(length = 60, nullable = false)
	public String userName;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	public Date loginDate;
	
	@Column(length = 20, nullable = false)
	public String errorCode;
	
	@Column(length = 250, nullable = false)
	public String errorDesc;

}