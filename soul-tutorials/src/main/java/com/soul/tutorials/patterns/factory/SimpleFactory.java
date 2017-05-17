package com.soul.tutorials.patterns.factory;

interface Car {
	void drive();
}

class Benz implements Car {

	@Override
	public void drive() {
		System.out.println("我开奔驰!");
	}

}

class Bmw implements Car {

	@Override
	public void drive() {
		System.out.println("我开宝马!");
	}

}
class Driver{
	public static Car driverCar(String brand) throws Exception{
		if ("Benz".equals(brand)) {
			return new Benz();
		}else if("Bmw".equals(brand)) {
			return new Bmw();
		}
		throw new Exception("No this brand Car!");
	}
}
public class SimpleFactory {
	
	public static void main(String[] args) {
		try {
			Driver.driverCar("Benz").drive();
			Driver.driverCar("Bmw").drive();
			Driver.driverCar("Bmww").drive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
