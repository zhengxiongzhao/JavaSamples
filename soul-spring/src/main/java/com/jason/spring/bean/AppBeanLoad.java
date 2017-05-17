package com.jason.spring.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;

	 
public  class AppBeanLoad   {
	
	private static final Logger logger=Logger.getLogger(AppBeanLoad.class);
	
   /**用文件系统的路径引用应用上下文
	 * @param classpath
	 * @return
	 */
	public static BeanFactory FileSystemXmlApplicationContext(String[] classpath){
		/*对于FileSystemXmlApplicationContext:
			默认表示的是两种:
			1.没有盘符的是项目工作路径,即项目的根目录;
			2.有盘符表示的是文件绝对路径.*/
	// 用文件系统的路径,默认指项目的根路径
	// ApplicationContext context = new FileSystemXmlApplicationContext("src/appcontext.xml");
    // ApplicationContext context = new FileSystemXmlApplicationContext("webRoot/WEB-INF/appcontext.xml");
	// 使用了classpath:前缀,这样,FileSystemXmlApplicationContext也能够读取classpath下的相对路径
	// ApplicationContext context = new FileSystemXmlApplicationContext("classpath:appcontext.xml");
    // ApplicationContext context = new FileSystemXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");
	   ApplicationContext context = new FileSystemXmlApplicationContext(classpath);
	   return context;
   }
   
	/**用文件系统的路径
	 * @param classpath
	 * <li>ClassPathXmlApplicationContext[只能读放在web-info/classes目录下的配置文件]和FileSystemXmlApplicationContext的区别
	 * @return 
	 */
	public static BeanFactory ClassPathXmlApplicationContext(String[] classpath){
		 // 用classpath路径
	    // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
	    // ApplicationContext context = new ClassPathXmlApplicationContext("appcontext.xml");
	    // ClassPathXmlApplicationContext使用了file前缀是可以使用绝对路径的
	    // ApplicationContext context = new ClassPathXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext(classpath);
		return context;
	}

	/**从WEB-INF下加载配置文件 
	 * @param request
	 * @return WebApplicationContextUtils<-ApplicationContext<-BeanFactory
	 */
	public static BeanFactory getWebApplicationContext(HttpServletRequest request){
		ApplicationContext  context= WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		return context;
	}
	
	
	
	//<------------------------------->
	/**
	 * 利用XmlBeanFactory(Resource resource) 这里Resource必须是xml格式
	 * Resource包括：AbstractResource, ClassPathResource, FileSystemResource,
	 * InputStreamResource, ServletContextResource, UrlResource
	*/
	
	
	/*
	 * *XmlBeanFactory 引用资源
 * 利用 FileSystemResource(String path)完整目录
 * src/applicationContext.xml
 */
	public static BeanDefinitionRegistry FileSystemResource(String loadPath){
		logger.debug(loadPath);
		Resource resource = new FileSystemResource(loadPath);
		 DefaultListableBeanFactory factory = new DefaultListableBeanFactory();    
		 XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);    
		 reader.loadBeanDefinitions(resource);    
		return reader.getBeanFactory();
	}
	
	/* path=src/applicationContext.xml
	 * XmlBeanFactory 引用获得spring资源，加载配置文件
	 * XmlBeanFactory deprecated as of Spring 3.1 in favor of {@link DefaultListableBeanFactory} and
	 * {@link XmlBeanDefinitionReader}
	 */
	public static BeanDefinitionRegistry ClassPathResource(String loadPath){
		/*@Deprecated
		Resource resource = new ClassPathResource(loadPath);
		XmlBeanFactory factory=new XmlBeanFactory(resource); */
		logger.debug(loadPath);
		 ClassPathResource res = new ClassPathResource(loadPath);    
		 DefaultListableBeanFactory factory = new DefaultListableBeanFactory();    
		 XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);    
		 reader.loadBeanDefinitions(res);    
		 return reader.getBeanFactory();
	}

	
	
	public static void main(String[] args) {
		BeanFactory bf= AppBeanLoad.ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		SimpleBean simple = (SimpleBean)bf.getBean("simple");
		simple.showHello();
		/*ResourceBean resourceBean = bf.getBean(ResourceBean.class);  
	    ResourceLoader loader = resourceBean.getResourceLoader();  
		System.out.println(loader instanceof ApplicationContext);*/
		
		
		/*BeanDefinitionRegistry bean = AppBeanLoad.FileSystemResource("src/applicationContext.xml");
		String[] ba = bean.getAliases("simple");
		System.out.println(ba[0]);
		  
		
		
		System.out.println(bean.getBeanDefinitionCount());
		String[] names = bean.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			  BeanDefinition bdf = bean.getBeanDefinition(names[i]);
			System.out.println(bdf.getBeanClassName());
			System.out.println(bdf.getParentName());
			System.out.println(bdf.getFactoryBeanName());
			System.out.println(bdf.getFactoryMethodName());
			System.out.println(bdf.getPropertyValues());
			//System.out.println(bdf.getPropertyValues().getPropertyValue("username").getValue());
			System.out.println(bdf.getResourceDescription());  
		}*/
	}
}
