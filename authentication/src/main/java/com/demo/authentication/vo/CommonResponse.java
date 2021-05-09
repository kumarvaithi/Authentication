package com.demo.authentication.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
	
	public String errorCode;
	public String errorDesc;

}