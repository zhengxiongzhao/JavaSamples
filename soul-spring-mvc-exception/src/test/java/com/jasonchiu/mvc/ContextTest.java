package com.jasonchiu.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jasonchiu.core.dao.TestDao;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContextTest extends ApplicationContextTest{
	
	@Test
	public void show(){
		TestDao td=(TestDao) context.getBean("testDao");
		try {
			td.exception(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
