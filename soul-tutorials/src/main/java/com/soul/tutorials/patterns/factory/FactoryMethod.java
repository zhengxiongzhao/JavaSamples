package com.soul.tutorials.patterns.factory;

interface DriverMethod {
	Car driverCar();
}

class BenzDriver implements DriverMethod {
	public Car driverCar() {
		return new Benz();
	}
}

class BwmDriver implements DriverMethod {
	public Car driverCar() {
		return new Bmw();
	}
}

public class FactoryMethod {

	public static void main(String[] args) {
		try {
			new BenzDriver().driverCar().drive();
			new BwmDriver().driverCar().drive();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
