package com.jason.spring.aop.simple;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.jason.spring.beans.AppBeanLoad;

public class AOPTest {
	
	public void annotationAspact() {
		BeanFactory ctx = AppBeanLoad
				.ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		IXMLService ser = ctx.getBean("xmlService", IXMLService.class);
		ser.addCustomer();
		// System.out.println(ser.addCustomerReturn());
		ser.addCustomerAround();
		try {
			// ser.addCustomerThrowException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void implementsAspact() {
		BeanFactory ctx = AppBeanLoad
				.ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		IXMLService ser = (IXMLService)ctx.getBean("xmlService");
		ser.addCustomer();
	}

}
