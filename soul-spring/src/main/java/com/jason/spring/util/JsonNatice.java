package com.jason.spring.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.jason.spring.aop.source.TargetAdvice;

public class JsonNatice {
	public static void main(String[] args) {
		TargetAdvice target= new TargetAdvice();
		System.out.println(toJson(target));
	}
	public static String toJson(Object obj){
		Class clazz=obj.getClass();
		if (obj instanceof java.lang.String) {
			return (String)obj;
		}
		Method[] methods= clazz.getDeclaredMethods();
		List result=new ArrayList();
		for (int i = 0; i < methods.length; i++) {
			Method method=methods[i];
			int modifiers = method.getModifiers();
			if (Modifier.isPublic(modifiers)) {
				result.add(method.getName()+":"+method.getDefaultValue());
			}
		}
		return result.toString();
	}

}
