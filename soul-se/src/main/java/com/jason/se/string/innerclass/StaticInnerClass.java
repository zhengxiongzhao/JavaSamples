package com.jason.se.string.innerclass;

public class StaticInnerClass {
	public StaticInnerClass() {
		System.out.println("class");
	}
	public static class InnerClass{
		InnerClass() {
			System.out.println("inner Class");
		}
		public void print(String str){
			System.out.println(str);
		}
	}
	public static void main(String[] args) {
		StaticInnerClass.InnerClass ic=new StaticInnerClass.InnerClass();
		ic.print("aaaaaaa");
		
	}
	

}
