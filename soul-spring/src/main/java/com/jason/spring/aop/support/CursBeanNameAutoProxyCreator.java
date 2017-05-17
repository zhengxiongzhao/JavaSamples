package com.jason.spring.aop.support;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;

public class CursBeanNameAutoProxyCreator extends BeanNameAutoProxyCreator {
	private static final long serialVersionUID = -3807372773388771514L;

	private String[] Interceptorys;
	
	@Override
	public void setBeanNames(String[] beanNames) {
		super.setBeanNames(beanNames);
	}

	@Override
	public void setInterceptorNames(String[] interceptorNames) {
		for (int i = 0; i < interceptorNames.length; i++) {
			for (int j = 0; j < Interceptorys.length; j++) {
				if (interceptorNames[i].equals(Interceptorys[i])) {
					super.setInterceptorNames(interceptorNames);
				}
			}
		}
	}
	 
	public void postProcessInstantition(){
		
	}

	public void setInterceptorys(String[] interceptorys) {
		Interceptorys = interceptorys;
	}
}
