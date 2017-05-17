package com.jason.spring.bean;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import sun.rmi.runtime.Log;

/**
 * 利用XmlBeanFactory(Resource resource) 这里Resource必须是xml格式
 * Resource包括：AbstractResource, ClassPathResource, FileSystemResource,
 * InputStreamResource, ServletContextResource, UrlResource
 */
public class MainBeanLoad {
	
	private final Logger logger=Logger.getLogger(MainBeanLoad.class);
	
	/**
 * 利用 FileSystemResource(String path)完整目录
 * src/applicationContext.xml
 */
	public  BeanFactory FileSystemResource(String loadPath){
		Resource resource = new FileSystemResource(loadPath);
		//引用资源用XmlBeanFactory
		return new XmlBeanFactory(resource);
		 
	}
	
	/**
	 * 获得spring资源，加载配置文件
	 * applicationContext.xml
	 */
	public BeanFactory ClassPathResource(){
		Resource resource1 = new ClassPathResource("applicationContext.xml");
		return new XmlBeanFactory(resource1);
	}
	
	
	public static void main(String[] args) {
		try {
			MainBeanLoad ml=new MainBeanLoad();
			BeanFactory factory= ml.ClassPathResource();
			System.out.println(factory.getBean("simpleBean"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
