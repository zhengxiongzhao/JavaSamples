package com.jason.spring.web.servlet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.AbstractUrlViewController;

public class CIHAbstractUrlViewController extends AbstractUrlViewController {
	//设置UrlPathHelper使用的查找路径的决议。
	//private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	//如果要设置URL查找总是使用当前servlet上下文内的完整路径。否则，在当前的servlet的映射路径用于如适用（在web. xml中的servlet映射的情况，即".../ "）。默认为“假”。
	//setAlwaysUseFullPath
	//setUrlDecode
	@Override
	protected String getViewNameForRequest(HttpServletRequest request) {
		request.setAttribute("hello","ho , AbstractUrlViewController");
		return "home";
	}

}
