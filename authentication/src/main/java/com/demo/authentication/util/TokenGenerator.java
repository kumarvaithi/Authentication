package com.demo.authentication.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TokenGenerator implements IdentifierGenerator {
	
	
	/**
	 * Desc : To generate Random UUID for Token Generate
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String token = UUID.randomUUID().toString();
		return token; 
	}

}