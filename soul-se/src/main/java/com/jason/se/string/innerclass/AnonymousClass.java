package com.jason.se.string.innerclass;

public class AnonymousClass {
	public void show() {
		Thread sta=new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
				}
			}
		};
		System.out.println("Start");
		sta.start();
	}
	public static void main(String[] args) {
		new AnonymousClass().show();
	}
}
