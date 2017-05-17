package com.jason.hibernate.onetomany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.model.onetomany.bidricational.Dream;
import com.jason.hibernate.model.onetomany.bidricational.User;
import com.jason.hibernate.utils.HibernateUtil;

public class UserSaves {
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
	public void bidiSave(){
		User user=new User();
		user.setAge(11);
		user.setName("aaa");
		Dream dream=new Dream();
		dream.setName("cccccc");
		dream.setUser(user);
		session.save(dream);
	}
	
	public void undiSave(){
		User user=new User();
		user.setAge(11);
		user.setName("aaa");
		Dream dream=new Dream();
		dream.setName("bbbbbbb");
		dream.setUser(user);
		user.getDreams().add(dream);
		session.save(user);
	}
	
	public void getUser(){
		/*Dream dream = (Dream)session.get(Dream.class,12 );
		System.out.println(dream.getName());
		System.out.println(dream.getUser().getName());*/
		
		
		User user = (User)session.get(User.class,11 );
		System.out.println(user.getName());
		/*Iterator<Dream> iter = user.getDreams().iterator();
		while (iter.hasNext()) {
			Dream dream = (Dream) iter.next();
			System.out.println(dream.getName());
		}*/
	}
	
	@AfterClass
	public static void afterClass() {
		try {
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Transaction tx = session.getTransaction();
			if (tx!=null) {
				tx.commit();
			}
			if (sf!=null) {
				sf.close();
			}
			try {
				HibernateUtil.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
