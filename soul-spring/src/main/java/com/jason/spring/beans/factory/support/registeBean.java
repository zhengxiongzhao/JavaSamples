package com.jason.spring.beans.factory.support;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;

public class registeBean extends DefaultListableBeanFactory {
	private final XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(this);
	public registeBean(Resource resource){
		this.reader.loadBeanDefinitions(resource);
	}
	public static void main(String[] args) throws Exception {
		/*ClassPathResource resource=new ClassPathResource("applicationContext.xml");
		registeBean re=new registeBean(resource);
		SimpleBean simplebean = (SimpleBean)re.getBean("simple");
		simplebean.showHello();
		 Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		 document.createComment("");*/
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.HOUR,0);
		
		for (int i = 0; i < 168; i++) {
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+5);
			System.out.print(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+",");
		}
	}
}