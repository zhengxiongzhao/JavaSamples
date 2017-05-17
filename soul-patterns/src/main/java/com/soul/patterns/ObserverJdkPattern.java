package com.soul.patterns;

import java.util.Observable;

import com.soul.patterns.ObserverJdkPattern.Observer;

public class ObserverJdkPattern {
	class Observer extends Observable{

		@Override
		public synchronized void addObserver(java.util.Observer o) {
			super.addObserver(o);
		}

		@Override
		public synchronized void deleteObserver(java.util.Observer o) {
			// TODO Auto-generated method stub
			super.deleteObserver(o);
		}

		@Override
		public void notifyObservers() {
			// TODO Auto-generated method stub
			super.notifyObservers();
		}

		@Override
		public void notifyObservers(Object arg) {
			// TODO Auto-generated method stub
			super.notifyObservers(arg);
		}

		@Override
		public synchronized void deleteObservers() {
			// TODO Auto-generated method stub
			super.deleteObservers();
		}

		@Override
		protected synchronized void setChanged() {
			// TODO Auto-generated method stub
			super.setChanged();
		}

		@Override
		protected synchronized void clearChanged() {
			// TODO Auto-generated method stub
			super.clearChanged();
		}

		@Override
		public synchronized boolean hasChanged() {
			// TODO Auto-generated method stub
			return super.hasChanged();
		}

		@Override
		public synchronized int countObservers() {
			// TODO Auto-generated method stub
			return super.countObservers();
		}
		
	}
	
	class CurrentCondition implements java.util.Observer {

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("通知到了!");
		}
		
	}
	
	public static void main(String[] args) {
		Observer observa = new ObserverJdkPattern().new Observer();
		observa.addObserver(new ObserverJdkPattern().new CurrentCondition());
		observa.notifyObservers();
	}
}
