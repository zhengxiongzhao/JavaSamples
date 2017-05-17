package com.soul.patterns;

import java.util.ArrayList;

public class ObserverPattern {

	class Subject {
		private ArrayList<Observer> list = new ArrayList<Observer>();
		private String importantSubjectData = "Initial";
		public String getImportantSubjectData() {
			return importantSubjectData;
		}
		public void setImportantSubjectData(String importantSubjectData) {
			this.importantSubjectData = importantSubjectData;
		}

		public void attach(Observer observer){
			list.add(observer);
			observer.subject = this;
		}
		
		public boolean detach(Observer observer){
			return list.remove(observer);
		}
		
		public void notification(){
			for (Observer o : list) {
				o.update();
			}
		}
	}
	class ConcreteSubject extends Subject{
		private int subjectState = -1;
		public int getState() {
			return subjectState;
		}
		public void setState(int subjectState) {
			this.subjectState = subjectState;
		}
	}
	
	abstract class  Observer{
		protected Subject subject;
		public Subject getSubject() {
			return subject;
		}
		public void setSubject(Subject subject) {
			this.subject = subject;
		}
		public abstract void update();
	}
	
	class ConcreteObserver extends Observer{
		private String observerName;

		public ConcreteObserver(String observerName) {
			this.observerName = observerName;
		}
		@Override
		public void update() {
			System.out.println(String.format("In Observer %s: data from subject = %s", observerName, subject.importantSubjectData));
		}
	}
	public static void main(String[] args) {
		// Set up everything
		Subject s = new ObserverPattern().new ConcreteSubject();
		Observer o1 = new ObserverPattern().new ConcreteObserver("first observer");
		Observer o2 = new ObserverPattern().new ConcreteObserver("second observer");
		Observer o3 = new ObserverPattern().new ConcreteObserver("three observer");

		System.out.println("test attach observer");
		s.attach(o1);
		s.attach(o2);
		s.attach(o3);
		
		
		// make changes to subject
		s.setImportantSubjectData("This is important subject data");

		// Notify all observers
		s.notification();	
		
		System.out.println("test detach observer");
		s.detach(o3);
		s.notification();	
	}
}
