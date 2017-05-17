package com.jason.spring.beans.factory.support;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope
{

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
