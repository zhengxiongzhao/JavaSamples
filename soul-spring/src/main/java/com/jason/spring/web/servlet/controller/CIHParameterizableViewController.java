package com.jason.spring.web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/** 在创建控制器时， 你不想将视图名称硬编码在控制器中，而是把它参数化， 以便在Bean配置文件中进行指定。
 * ParameterizableViewController是AbstractController的一个子类，其中定义了viewName属性及其getter和setter方法。
 * 对于不需要任何处理逻辑，只为用户呈现视图控制器，可以直接使用这个控制器类，或者扩展这个控制器类来继承viewName属性。
 * 假设有一个非常简单的控制器，其目的只是为了呈现hello视图。
 * 可以声明一个ParameterizableViewController类型的控制器，并指定viewName属性为hello。
* @ClassName: CIHParameterizableViewController
* @author CIH CIHUnKnown@Gmail.com
* @date 2013-4-22 下午2:25:59
* 
*/
public class CIHParameterizableViewController extends
 		ParameterizableViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view=new ModelAndView(getViewName());
		view.addObject("hello", "它纯粹是一个重定向类，仅此而已，无所不及");
		return view;
	}

	 


	 
	
}
