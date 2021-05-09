package com.demo.authentication.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.demo.authentication.vo.CommonVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDetails {
	
	@Id
	@GenericGenerator(name = "gen_token", strategy = "com.demo.authentication.util.TokenGenerator")
	@GeneratedValue(generator = "gen_token")
	@Column(length = 36)
	public String token;
	
	@Column(length = 30, nullable = false)
	public String systemId;
	
	@Column(nullable = false)
	public int status;
	
	@Embedded
	public CommonVO commonVO;

}
