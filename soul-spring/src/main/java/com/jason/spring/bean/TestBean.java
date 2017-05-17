package com.jason.spring.bean;

public class TestBean {
	//它是在WebApplicationContext已经存在的情况下进行的，也就意味着在初始化它的时候，IOC容器应该已经工作了，这也是我们在web.xml中配置Spring的时候，需要把DispatcherServlet的 load-on-startup的属性配置为2的原因。 
}
