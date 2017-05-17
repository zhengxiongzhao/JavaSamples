package com.jason.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.utils.HibernateUtil;

public class OneUserTest {
	private static SessionFactory sf = null;
	private static Session session = null;

	@BeforeClass
	public static void befforeClass() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}
	
	@AfterClass
	public static void afterClass() {
		session.getTransaction().commit();
		sf.close();
		HibernateUtil.shutdown();
	}
	
	@Test
	public void show(){
		SchemaExport se = new SchemaExport(
				new Configuration().configure());
		se.create(true, true);
	}
}
