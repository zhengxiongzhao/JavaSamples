package com.jason.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.web.exceptions.BusinessException;
import com.jason.web.exceptions.ParameterException;
import com.jason.web.manager.ExceptionService;

@Controller
public class ExceptionController
{
	@Resource(name = "testService")
	private ExceptionService testService;

	@RequestMapping(value = "/controller.do", method = RequestMethod.GET)
	public void controller(HttpServletResponse response, Integer id)
			throws Exception
	{
		switch (id) {
		case 1:
			throw new BusinessException("10", "controller10");
		case 2:
			throw new BusinessException("20", "controller20");
		case 3:
			throw new BusinessException("30", "controller30");
		case 4:
			throw new BusinessException("40", "controller40");
		case 5:
			throw new BusinessException("50", "controller50");
		default:
			throw new ParameterException("Controller Parameter Error");
		}
	}

	@RequestMapping(value = "/service.do", method = RequestMethod.GET)
	public void service(HttpServletResponse response, Integer id)
			throws Exception
	{
		testService.exception(id);
	}

	@RequestMapping(value = "/dao.do", method = RequestMethod.GET)
	public void dao(HttpServletResponse response, Integer id) throws Exception
	{
		testService.dao(id);
	}
}