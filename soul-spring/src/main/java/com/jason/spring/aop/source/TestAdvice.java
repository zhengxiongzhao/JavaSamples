package com.jason.spring.aop.source;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.jason.spring.beans.di.MethodNameMatchPointcat;

public class TestAdvice {
	
	public Object adviceConfig(String pointcutExprossion,Advice advice,Object target){
		//配置切入点
//		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//		pointcut.setExpression(pointcutExprossion);
		//定义切面
		Pointcut pointcut=new MethodNameMatchPointcat();
		//切面
		Advisor advisor=new DefaultPointcutAdvisor(pointcut,advice);
		//代理
		ProxyFactory proxyFactory=new ProxyFactory();
		proxyFactory.addAdvisor(advisor);
		proxyFactory.setTarget(target);
		return proxyFactory.getProxy();
	}
	
	
	/**
	 * 后置返回通知
	 * @throws Exception 
	 */
	@Test
	public void AdviceProcess() throws Exception {
		TargetAdvice target= new TargetAdvice();
		String pointcutExprossion="execution(* com.cihunknown.spring.aop.source.*.*(..))";
		Object proxyObj=adviceConfig(pointcutExprossion, new BaseAdvice(), target);
		TargetAdvice obj = (TargetAdvice) proxyObj;
		obj.show(TargetAdvice.AFTERRETURNING);
	}
	
	
}

