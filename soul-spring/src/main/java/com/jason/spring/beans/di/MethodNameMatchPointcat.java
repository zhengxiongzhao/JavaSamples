package com.jason.spring.beans.di;

import java.lang.reflect.Method;

import org.springframework.aop.support.NameMatchMethodPointcut;

/** 
 * 定义一个切点，指定对应方法匹配。来供切面来针对方法进行处理<br>
 * 继承NameMatchMethodPointcut类，来用方法名匹配
 * @author Administrator
 *
 */
public class MethodNameMatchPointcat extends NameMatchMethodPointcut {
	private static final long serialVersionUID = 3732478902593156822L;

	@Override
	public boolean matches(Method method, Class targetClass) {
		 // 设置单个方法匹配
        this.setMappedName("show");
        //也可以用“ * ” 来做匹配符号
        this.setMappedName("show*");

        // 设置多个方法匹配
        String[] methods = { "show", "display" };
        this.setMappedNames(methods);
        
		return super.matches(method, targetClass);
	}

}
