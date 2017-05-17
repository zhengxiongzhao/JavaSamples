package com.soul.tutorials.patterns.strategy.behavior;

public class BadFlyBehavior implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("not fly!");
	}

}
