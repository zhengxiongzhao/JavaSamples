package com.soul.patterns;

/**
 * 策略模式
 * <p>
 * 对象替换
 * </p>
 * 
 * @author zxzhao
 * 
 */
public class StrategyPattern {

	class Context {
		private Strategy s;

		public Context(Strategy s) {
			this.s = s;
		}

		public void doWork() {

		}

		public void doStrategyWork() {
			s.doAlgorithm();
		}
	}

	abstract class Strategy {
		public abstract void doAlgorithm();
	}

	class FirstStrategy extends Strategy {

		@Override
		public void doAlgorithm() {
			System.out.println("In first strategy!");
		}

	}

	class SecondStrategy extends Strategy {

		@Override
		public void doAlgorithm() {
			System.out.println("In second strategy!");
		}

	}

	class Client {
		public void worker() {
			new Context(new FirstStrategy()).doStrategyWork();
			new Context(new SecondStrategy()).doStrategyWork();
		}
	}

	public static void main(String[] args) {
		new StrategyPattern().new Client().worker();

	}
}
