package com.demo.authentication.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserMasterIdGenerator implements IdentifierGenerator {

	/**
	 * @Desc To Generate unique id for Every User with Date time
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String systemId = simpleDateFormat.format(new Date());
		return systemId; 
	}

}