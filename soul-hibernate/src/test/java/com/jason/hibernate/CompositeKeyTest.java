package com.jason.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.model.composite.CompositeKey;
import com.jason.hibernate.model.composite.CompositeKeyId;
import com.jason.hibernate.utils.HibernateUtil;

public class CompositeKeyTest {
	private static SessionFactory sf = null;
	private static Session session = null;

	@BeforeClass
	public static void befforeClass() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}
	@Test
	public void show(){
		CompositeKeyId myCompositeKeyId = new CompositeKeyId();
		myCompositeKeyId.setId(1231);
		myCompositeKeyId.setName("aaaaaaaa");
		CompositeKey compositeKey = new CompositeKey();
		compositeKey.setAge(11);
		compositeKey.setDescription("ssssssssssssssss");
//		compositeKey.setMyCompositeKeyId(myCompositeKeyId);
		session.save(compositeKey);
	}

	@Test
	public void test() {
		CompositeKey compositeKey = new CompositeKey();
		compositeKey.setId(111);
		compositeKey.setName("aaaaaa");
		compositeKey.setAge(11);
		compositeKey.setDescription("ssssssssssssssss");
		session.save(compositeKey);
	}

	@AfterClass
	public static void afterClass() {
		session.getTransaction().commit();
		sf.close();
		HibernateUtil.shutdown();
	}
}
