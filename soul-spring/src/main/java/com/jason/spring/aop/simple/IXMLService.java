package com.jason.spring.aop.simple;

public interface IXMLService {
	void addCustomer();

	String addCustomerReturn();

	void addCustomerThrowException() throws Exception;

	void addCustomerParam(String paramName);
	
	public void addCustomerAround();
}
