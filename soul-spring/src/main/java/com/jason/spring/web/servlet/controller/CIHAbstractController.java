package com.jason.spring.web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

/**

简单
Controller(接口)
AbstractController		控制器非常简单，需要的功能和java applet差不多

一次性
ThrowawayController		你需要像命令那样的简单请求处理方式（类似Webwork的Action）

多动作
MultiActionController		应用系统中有多个动作处理相似或相关的逻辑

命令
BaseCommandController
AbstractCommandController	控制器会从请求接受一个或多个参数，然后将他们绑定到一个对象中，而且提供参数验证的功能

表单
AbstractFormController
SimpleformController		需要给用户显示一个表单，并且处理输入表单的数据

向导
AbstractWizardFormController	你需要带领用户通过一个复杂的，多页面的输入表单，最后按一个表单处理
* @ClassName: CIHAbstractController
* @author CIH CIHUnKnown@Gmail.com
* @date 2013-4-20 下午11:55:45
* @see WebContentInterceptor
* @see synchronizeOnSession
*/
public class CIHAbstractController extends AbstractController {
	
	/*
	//<!-------------------------WebContentGenerator-------------------------------->
	*//** HTTP method "GET" *//*
	private static final String METHOD_GET = "GET";
	*//** HTTP method "HEAD" *//*
	private static final String METHOD_HEAD = "HEAD";
	*//** HTTP method "POST" *//*
	private static final String METHOD_POST = "POST";
	private static final String HEADER_PRAGMA = "Pragma";
	private static final String HEADER_EXPIRES = "Expires";
	private static final String HEADER_CACHE_CONTROL = "Cache-Control";
	*//**
	* @Fields supportedMethods : TODO(指定这个控制器应该接受什么样的请求方法。通常它被设置成同时支持GET和POST，但是可以选择你想支持的方法。如果控制器不支持请求发送的方法， 客户端会得到通知（通常是抛出一个ServletException）。 )
	*//*
	private Set<String>	supportedMethods;
	*//**
	* @Fields requireSession : TODO(表明这个控制器是否需要HTTP session才能正常工作。如果控制器在没有session的情况下接收到请求，客户端会因为抛出ServletException 而得到通知。 )
	*//*
	private boolean requireSession = false;
	*//**
	* @Fields cacheSeconds : TODO(指定controller通知客户端对数据内容缓存的秒数，一般为大于零的整数。默认值为-1，即不缓存。)
	*//*
	private int cacheSeconds = -1;
	private boolean alwaysMustRevalidate = false;
	*//** Use HTTP 1.0 expires header? *//*
	private boolean useExpiresHeader = true;
	*//** Use HTTP 1.1 cache-control header? *//*
	private boolean useCacheControlHeader = true;
	*//** Use HTTP 1.1 cache-control header value "no-store"? *//*
	private boolean useCacheControlNoStore = true;

	
	//<!-------------------------AbstractController-------------------------------->
	*//**
	* @Fields synchronizeOnSession : TODO(指定controller是否同步用户的HTTP session。)
	*//*
	private boolean synchronizeOnSession = false;*/
	
	
	/* (Template method. Subclasses must implement this.)
	* <p>Title: handleRequestInternal</p>
	* @throws Exception
	* @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView=new ModelAndView("home");
		modelAndView.addObject("hello", "oh AbstractController!");
		return modelAndView;
	}

}
