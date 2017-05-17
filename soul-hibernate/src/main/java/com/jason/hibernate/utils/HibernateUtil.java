package com.jason.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("all")
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private HibernateUtil() {}
	private static class BuildConfiguration{
		private static final HibernateUtil HIBERNATE_UTIL=new HibernateUtil();
	}
	public static HibernateUtil getInstance(){
		return BuildConfiguration.HIBERNATE_UTIL;
	}
	
	
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}