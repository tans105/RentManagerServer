package com.rentmanager.utils;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.rentmanager.dao.LoginDao;
import com.rentmanager.entity.database.Login;
/**
 * 
 * @author tanmay
 *
 */
public class UserIdentityGenerator implements IdentifierGenerator {
	private static final Integer ABBR_LENGTH = 3;

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Login login = (Login) object;
		LoginDao dao = new LoginDao();
		String maxUserId = dao.getMaxUserId(login.getRoleId());
		Serializable result = null;
		result = incrementUserId(maxUserId);
		return result;
	}

	private String incrementUserId(String maxUserId) {
		return maxUserId.substring(0, 3) + String.format("%010d", Integer.parseInt(maxUserId.substring(ABBR_LENGTH)) + 1);
	}

}
