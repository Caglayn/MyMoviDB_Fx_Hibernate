package com.caglayan.fxmoviedb.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		try {
			Configuration configuration = new Configuration();

			// Entity classes

			// composition - relations

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
