package com.jason.spring.web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;

public class CIHUrlFilenameViewController extends UrlFilenameViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("hello","oh , UrlFilenameViewController!");
		return super.handleRequestInternal(request, response);
	}

	 

}
