package com.soul.tutorials.patterns.strategy.behavior;

public class GoodFlyBehavior implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("happly fly!");
	}

}
