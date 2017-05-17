package com.jasonchiu.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jasonchiu.core.exception.BusinessException;
import com.jasonchiu.core.exception.ParameterException;


public class BaseController {
	
	/** 基于@ExceptionHandler异常处理 */
	//(value = { BusinessException.class, ParameterException.class, Exception.class})
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		
		request.setAttribute("ex", ex);
		
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			return "env/error-business";
		}else if(ex instanceof ParameterException) {
			return "env/error-parameter";
		} else {
			return "env/error";
		}
	}
}