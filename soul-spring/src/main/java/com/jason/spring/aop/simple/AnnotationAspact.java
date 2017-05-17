package com.jason.spring.aop.simple;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationAspact {
	private final Log logger = LogFactory.getLog(getClass());
	//前置通知
	@After(value="execution(* com.cihunknown.spring.aop.simple.XMLService.*(..) and args(param))",argNames="param")
	public void beforeAdvice() {
		logger.warn("----------beforeAdvice-------------");
		System.out.println("beforeAdvice");
	}
	
	
	//后置返回通知
	 @AfterReturning( 
			pointcut="execution(* com.cihunknown.spring.aop.simple.*.*(..))",
			returning="value")
	public void afterReturning(JoinPoint joinPoint, Object value){
		System.out.println("value:"+value);
		System.out.println("Args:"+Arrays.toString(joinPoint.getArgs()) +"     MethodInvocationProceedingJoinPoint");
		System.out.println("class:"+joinPoint.getClass());
		System.out.println("Signature 方法:"+joinPoint.getSignature());
		System.out.println("Kind:"+joinPoint.getKind()+"  [method-execution] expression属性定义切入点模式，默认是AspectJ语法   ");
		System.out.println("Target class:"+joinPoint.getTarget());
	} 
	
	//后置异常通知
	@AfterThrowing(
			pointcut="execution(* com.cihunknown.spring.aop.simple.*.*(..))",
			throwing="error")
	public void afterThrowsException(JoinPoint joinPoint, Throwable error){  
        System.out.println("Throwable:"+error);
		System.out.println("Signature:"+joinPoint.getSignature());
		System.out.println("Target:"+joinPoint.getTarget());
		logger.warn("----------afterThrowsException-------------"); 
	}
	
	//
	@Around(value="execution(* com.cihunknown.spring.aop.simple.*.*(..))")
	public void aroundAdvice(ProceedingJoinPoint joinPoint){
		logger.warn("----------aroundAdvice-------------");
		System.out.println("logAround() is running!");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		System.out.println("Around after is running!");
		System.out.println("******");
	}
	  
	
	//后置通知
	//后置最终通知
	@Before("execution(* com.cihunknown.spring.aop.simple.*.*(..))")
	public void afterFanitionAdvice() {
		System.out.println("afterFanitionAdvice");
		logger.warn("----------afterFanitionAdvice-------------");
	}
	
	
}
