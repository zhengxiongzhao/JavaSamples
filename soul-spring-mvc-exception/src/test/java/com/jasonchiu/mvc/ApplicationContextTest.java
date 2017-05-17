package com.jasonchiu.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/resources/spring/applicationContext.xml" })
public class ApplicationContextTest {
	@Autowired
	protected ApplicationContext context;
}
