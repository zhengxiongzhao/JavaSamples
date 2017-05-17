package com.soul.tutorials.patterns.strategy;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import com.soul.tutorials.patterns.strategy.behavior.FlyBehavior;

public abstract class Bird {
	private FlyBehavior flyBehavior;
	protected void setBehavior(FlyBehavior flyBehavior){
		this.flyBehavior = flyBehavior;
	};
	
	public void fly() throws NotImplementedException{
		if (flyBehavior==null) {
			throw new NotImplementedException("not set FlyBehavior!"); 
		}
		flyBehavior.fly();
	}
	
	public abstract void display();
	
	public void swin(){
		System.out.println("~~swin~~");
	}
	
	public void quack(){
		System.out.println("~~quack~~");
	}
}
