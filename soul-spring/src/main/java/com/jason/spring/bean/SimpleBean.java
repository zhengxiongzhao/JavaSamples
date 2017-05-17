package com.jason.spring.bean;

import java.beans.ConstructorProperties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SimpleBean implements BeanLoad {
	
	private static final Logger logger=Logger.getLogger(SimpleBean.class);
	
	public SimpleBean() {
		logger.info("init "+SimpleBean.class.getName()+" .........");
		System.out.println(getClass().getName());
	}
	@ConstructorProperties({"str"})
	public SimpleBean(String str){
		logger.info(str);
	}

	private String hello;
	@Test
	public void showHello(){
		logger.debug("showHello");
		System.out.println("showHello");
	}
	
	public String getHello() {
		logger.info("getHello");
		return hello;
	}
	 
	public void setHello(String hello) {
		logger.info("setHello:"+hello);
		this.hello = hello;
	}
	
	private ResourceLoader resourceLoader;

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	 
}
