package com.jason.spring.aop.simple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XMLService implements IXMLService {
	private final Log logger=LogFactory.getLog(getClass());
	@Override
	public void addCustomer() {
		logger.warn("-------------addCustomer()---------------");
		System.out.println("addCustomer");
	}
	@Override
	public void addCustomerParam(String paramName) {
		logger.warn("-------------Param()---------------");
		System.out.println("addCustomerParam:"+paramName);
	}
	@Override
	public String addCustomerReturn() {
		logger.warn("-------------return()---------------");
		System.out.println("addCustomer");return "param";
	}
	@Override
	public void addCustomerThrowException() throws Exception {
		logger.warn("-------------ThrowException()---------------");
		throw new Exception("error");
	}
	@Override
	public void addCustomerAround() {
		logger.warn("-------------addCustomerAround()---------------");
		System.out.println("addCustomerAround");
	}
}
