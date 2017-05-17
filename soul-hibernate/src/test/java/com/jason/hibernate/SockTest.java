package com.jason.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.model.idgenerator.Stock;
import com.jason.hibernate.model.idgenerator.Stock.Yno;
import com.jason.hibernate.utils.HibernateUtil;

public class SockTest {
	private static SessionFactory sf = null;
	private static Session session = null;

	@BeforeClass
	public static void befforeClass() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}
	
	
	
	//@Test
	public  void test(){
		 Stock st=new Stock();
		st.setStockName("aaaaaaa");
		st.setStockCode("ccccccc");
		st.setOneDate(new Date());
		st.setThreeDate(new Date());
		st.setTowDate(new Date());
		st.setYn(Yno.YES); 
		session.save(st);
	}
	
	@Test
	public void updateTest(){
		Stock st=(Stock) session.get(Stock.class, 1);
		st.setStockCode("xx");
		session.update(st);
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
