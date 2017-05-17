package com.jasonchiu.context;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import com.jasonchiu.core.dao.TestDao;

public class AppXMLBeanReader {
	public static void main(String[] args) {
		ClassPathResource resource=new ClassPathResource("applicationContext.xml");
		DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanReader=new XmlBeanDefinitionReader(beanFactory);
		beanReader.loadBeanDefinitions(resource);
		TestDao td=(TestDao) beanFactory.getBean("testDao");
		try {
			td.exception(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
