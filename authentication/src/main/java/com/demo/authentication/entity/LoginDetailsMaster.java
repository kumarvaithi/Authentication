package com.demo.authentication.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.demo.authentication.vo.CommonVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetailsMaster {

	@Id
	@Column(length = 80)
	public String loginId;
	
	@Embedded
	public CommonVO commonVO;
	
	@ManyToOne
	@JoinColumn(name = "systemId")
	public UsersDetailsMaster usersDetailsMaster;
	
}