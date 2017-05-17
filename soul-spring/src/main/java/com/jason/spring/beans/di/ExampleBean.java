package com.jason.spring.beans.di;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleBean {
	private Map map;
	private List list;
	private Set set;
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
}
