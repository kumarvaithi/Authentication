package com.demo.authentication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.demo.authentication.vo.CommonVO;
import com.demo.authentication.vo.PINDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetailsMaster {

	@Id
	@GenericGenerator(name = "gen_system_id",strategy = "com.demo.authentication.util.UserMasterIdGenerator")
	@GeneratedValue(generator = "gen_system_id")
	@Column(length = 30, nullable = false)
	public String systemId;
	
	@Column(length = 60, nullable = false)
	public String userName;
	
	@Column(nullable = false)
	public int status;
	
	@OneToMany(mappedBy = "usersDetailsMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<LoginDetailsMaster> loginDetails;
	

	@Embedded
	public PINDetails pinDetails;
	
	@Embedded
	public CommonVO commonVO;
	
}