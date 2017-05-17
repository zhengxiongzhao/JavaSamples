package com.jason.spring.aop.simple;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**切面通知
 * @author Administrator
 *
 */
public class XMLAspact  {
	private final Logger logger = Logger.getLogger(XMLAspact.class);

	//前置通知
	public void beforeAdvice(String value) {
		System.out.println(value);
		logger.warn("----------beforeAdvice-------------");
		System.out.println("beforeAdvice");
	}
	//后置返回通知
	public Object afterReturning(JoinPoint joinPoint, Object value){
		System.out.println("value:"+value);
		System.out.println("Args:"+Arrays.toString(joinPoint.getArgs()) +"     MethodInvocationProceedingJoinPoint");
		System.out.println("class:"+joinPoint.getClass());
		System.out.println("Signature 方法:"+joinPoint.getSignature());
		System.out.println("Kind:"+joinPoint.getKind()+"  [method-execution] expression属性定义切入点模式，默认是AspectJ语法   ");
		System.out.println("Target class:"+joinPoint.getTarget());
		return value;
	} 
	//后置异常通知
	public void afterThrowsException(JoinPoint joinPoint, Throwable error){  
        System.out.println("Throwable:"+error);
		System.out.println("Signature:"+joinPoint.getSignature());
		System.out.println("Target:"+joinPoint.getTarget());
		logger.warn("----------afterThrowsException-------------"); 
	}
	//环绕通知
	public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.warn("----------aroundAdvice-------------");
		System.out.println("************************************Around Start!");
		joinPoint.proceed();
		System.out.println("************************************Around End!");
	}
	//后置通知
	//后置最终通知
	public void afterFanitionAdvice() {
		System.out.println("afterFanitionAdvice");
		logger.warn("----------afterFanitionAdvice-------------");
	}
	

}
