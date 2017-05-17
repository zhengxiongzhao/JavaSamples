package com.jason.spring.aop.source;

public class TargetAdvice {
	public static final int AFTEREXCPTION=4;
	public static final int AFTERRETURNING=3;
	public static final int AFTERFINALLA=5;
	public static final int BEFORE=1;
	public TargetAdvice() {}
	public String show(int args) {
		System.out.println("----------------targetAdvice----------------");
		if (args==AFTEREXCPTION) {
			int i=1/0;
			System.out.println(i);
		}
		return String.valueOf(args);
				
	}
}
