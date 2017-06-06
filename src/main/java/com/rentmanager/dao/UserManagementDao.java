package com.rentmanager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentmanager.entity.database.Login;
import com.rentmanager.utils.DbUtil;
import com.rentmanager.utils.HibernateUtils;

/**
@author : tanmay
@created : 06-Jun-2017
 */
public class UserManagementDao {
	
	public String saveEntity(Login login){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(login);
			tx.commit();
			tx = null;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			{
				if (tx != null) {
					tx.rollback();
					tx = null;
				}
				DbUtil.closeSession(session);
			}
		}
		return login.getUserId();
	}
}


