package com.rentalmanager.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rentalmanager.constants.Constants;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				logger.debug("Creating 1st Instance of Session Factory");
				Configuration configuration = new Configuration();
				configuration.configure(Constants.HIBERNATE_CONFIG_PATH);

				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sessionFactory;
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
			session = null;
		}
	}

}
