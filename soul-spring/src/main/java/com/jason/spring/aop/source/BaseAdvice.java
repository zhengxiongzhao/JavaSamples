package com.jason.spring.aop.source;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class BaseAdvice implements MethodBeforeAdvice,ThrowsAdvice,AfterReturningAdvice,MethodInterceptor {
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		System.out.println("-------------AfterReturn End-------------");
//		System.out.println("args:"+Arrays.toString(args));
//		System.out.println("returnValue:"+returnValue);
//		System.out.println("method:"+target.getClass().getName()+"."+method.getName()+"()" );
//		System.out.println("target:"+JsonNatice.toJson(target) );
	}

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
//		System.out.println("args:"+Arrays.toString(args));
//		System.out.println("method:"+target.getClass().getName()+"."+method.getName()+"()" );
//		System.out.println("target:"+JsonNatice.toJson(target) );
		System.out.println("-------------before Start-------------");
	}
	
	/**异常通知，接口没有包含任何方法。通知方法自定义
     * 通知方法，需要按照这种格式书写
     * 
     * @param method
     *            可选：切入的方法
     * @param args
     *            可选：切入的方法的参数
     * @param target
     *            可选：目标对象
     * @param throwable
     *            必填 : 异常子类，出现这个异常类的子类，则会进入这个通知。
     */
	public void afterThrowing(Method method, Object[] args, Object target, Throwable ex){
		System.out.println("-------------AfterThrowing End-------------");
//		System.out.println(ex.getMessage());
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
//		System.out.println("Method:"+invocation.getMethod());
//		System.out.println("Method arguments:"+invocation.getArguments());
		try {
			System.out.println("-------------Arround End -------------");
			Object obj=invocation.proceed();
			System.out.println("-------------Arround End -------------");
			return obj;
		} catch (Exception e) {
			System.out.println("Arrount Exception:"+e.getMessage());
			throw e;
		}
		
	}
	
	//void afterThrowing([Method, args, target], ThrowableSubclass);
	//Some examples of valid methods would be: 
//	public void afterThrowing(Exception ex){System.out.println(ex.getMessage()+"Exception");};
//	public void afterThrowing(RemoteException ex){};
	/*public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable){
		System.out.println("-------------Method End Throwable-------------");
		System.out.println(throwable.getMessage());
	}*/
//	public void afterThrowing(Method method, Object[] args, Object target, ServletException ex){};

}
