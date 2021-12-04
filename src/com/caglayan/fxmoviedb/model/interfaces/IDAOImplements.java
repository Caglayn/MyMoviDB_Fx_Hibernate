package com.caglayan.fxmoviedb.model.interfaces;

import org.hibernate.Session;

import com.caglayan.fxmoviedb.utils.HibernateUtil;


public interface IDAOImplements<T> {
	default Session getHibernateSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
