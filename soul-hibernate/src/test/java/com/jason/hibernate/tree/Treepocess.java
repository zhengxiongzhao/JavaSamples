package com.jason.hibernate.tree;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jason.hibernate.model.tree.Province;
import com.jason.hibernate.utils.HibernateUtil;

public class Treepocess {
	private static SessionFactory sf = null;
	private static Session session = null;

	@BeforeClass
	public static void befforeClass() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}

	@Test
	public void testGet(){
		Province p = (Province)session.get(Province.class,1);
		iter(p, 0);
	}
	
	private void iter(Province pro,int level){
		String str="";
		for (int i = 0; i <level; i++) {
			str+="--";
		}
		System.out.println(str+pro.getName());
		for (Province p : pro.getChildren()) {
			iter(p, level+1);
		}
	}
	
	public void testSave(){
		Province p=new Province();
		p.setName("����");
		
		Province p3=new Province();
		p3.setName("����");
		p3.setParent(p);
		p.getChildren().add(p3);
		Province p4=new Province();
		p4.setName("����");
		p4.setParent(p);
		p.getChildren().add(p4);
		Province p5=new Province();
		p5.setName("�ҽ�");
		p5.setParent(p);
		p.getChildren().add(p5);

		Province p1=new Province();
		p1.setName("����");
		
		Province p9=new Province();
		p9.setName("���");
		p9.setParent(p1);
		p1.getChildren().add(p9);
		
		
		Province p10=new Province();
		p10.setName("��Ϫ");
		p10.setParent(p3);
		p3.getChildren().add(p10);
		
		
		session.save(p);
		session.save(p1);
		
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
