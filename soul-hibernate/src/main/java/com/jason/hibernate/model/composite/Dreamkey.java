package com.jason.hibernate.model.composite;

import java.io.Serializable;

public class Dreamkey implements Serializable {
	private static final long serialVersionUID = -5338072797675543218L;
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
