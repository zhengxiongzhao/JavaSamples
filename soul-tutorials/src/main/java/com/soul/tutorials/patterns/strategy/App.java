package com.soul.tutorials.patterns.strategy;

import com.soul.tutorials.patterns.strategy.behavior.BadFlyBehavior;
import com.soul.tutorials.patterns.strategy.behavior.GoodFlyBehavior;

public class App {
	public static void main(String[] args) {
		Bird ostrichBird = new OstrichBird();
		Bird sparrowBird = new SparrowBird();
		
		ostrichBird.display();
		ostrichBird.swin();
		ostrichBird.quack();
		ostrichBird.setBehavior(new BadFlyBehavior());
		ostrichBird.fly();
		
		System.out.println("");
		
		sparrowBird.display();
		sparrowBird.swin();
		sparrowBird.quack();
		//sparrowBird.setBehavior(new GoodFlyBehavior());
		sparrowBird.fly();
	}
}
