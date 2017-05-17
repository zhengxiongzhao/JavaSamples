package com.jason.spring.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.jason.spring.beans.SimpleBean;

@SuppressWarnings("all")
public class TestPackage {
	public static void main(String[] args) {
		
		
		 //用文件系统的路径引用应用上下文
	    ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	    SimpleBean simple = (SimpleBean)ac.getBean("simpleBean");
	    simple.setHello("hello");
		System.out.println(simple.getHello());
		
		//获得spring资源，加载配置文件
		/*Resource resource1 = new ClassPathResource("applicationContext.xml");
		BeanFactory factory1 = new XmlBeanFactory(resource1);
		 simple= (SimpleBean) factory1.getBean("simpleBean");
		simple.setHello("hello");
		System.out.println(simple.getHello());*/
		
		
		/*ApplicationContext context = new ClassPathXmlApplicationContext(
		        new String[] {"bean.xml"});
		// of course, an ApplicationContext is just a BeanFactory
		BeanFactory factory2 = (BeanFactory) context;
		System.out.println(factory2);*/
		
		
		
		//从WEB-INF下加载配置文件 
	    /*ServletContext servletContext = request.getSession().getServletContext(); 
	    ApplicationContext factory1= WebApplicationContextUtils.getWebApplicationContext(servletContext);
	     simple= (SimpleBean) factory1.getBean("simpleBean");*/
	}
	
	/*
	  spring在web环境中，java代码里需要得到ApplicationContext; 根据前期配置的不同，有两种方式：

（1）spring加载放到web.xml中配置：

     <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    则得到ApplicationContext用如下方式：

    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(Servlet Context);

    java代码中调用 ctx.getBean(String beanId);

(2) 直接声明：

public class BeanManager {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("appcontext.xml") ;

    public static Object getBean(String beanId) {
        return context.getBean(beanId);
    }
}

 在web.xml中写一个servlet，自动启动，init方法中调用一下BeanManager

  init()  throws ServletException {

       BeanManager bm = new BeanManager();//可选的，为的是在web应用启动的时候就让spring加载bean配置。

    // 否则会在第一次调用BeanManager的时候加载，影响一次速度。
	
	}
	
	  在java代码中使用 BeanManager.getBean(String beanId); 来获得bean实例。
	
	  优缺点分析：
	
	  第一种方式不利于需要在web容器下进行，不利于测试；
	
	  第二种方法与web容器无关，单元测试方便；
	  
	  
	  
	  
	  
	  @RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(locations = "classpath*:/spring-core.xml")
	public class UserServiceTest{
	    @Resource
	    UserService service; 
	    
	    *//**
	     * 保存测试
	     *//*
	    @Test
	    public void testSave(){
	        User user = new User();
	        service.save(user);
	    }

	}*/
}
