package com.caglayan.fxmoviedb.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.caglayan.fxmoviedb.model.entity.MovieEntity;
import com.caglayan.fxmoviedb.model.entity.RatingEntity;
import com.caglayan.fxmoviedb.model.entity.TagEntity;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		try {
			Configuration configuration = new Configuration();

			// Entity classes
			configuration.addAnnotatedClass(MovieEntity.class);
			configuration.addAnnotatedClass(RatingEntity.class);
			configuration.addAnnotatedClass(TagEntity.class);

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
