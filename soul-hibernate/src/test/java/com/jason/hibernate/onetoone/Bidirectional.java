package com.jason.hibernate.onetoone;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.model.onetomany.unidricational.User;
import com.jason.hibernate.utils.HibernateUtil;

public class Bidirectional {
	private static SessionFactory sf = null;
	private static Session session = null;

	@BeforeClass
	public static void befforeClass() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}

	@Test
	public void schemaExport() {
		SchemaExport se = new SchemaExport(
				new Configuration().configure());
		se.create(true, true);
	}
	@Test
	public void testSave(){
		User user=new User();
		user.setBithday(new Date());
		session.save(user);
	}
	
	@AfterClass
	public static void afterClass() {
		try {
			session.getTransaction().commit();
			sf.close();
			HibernateUtil.shutdown();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
