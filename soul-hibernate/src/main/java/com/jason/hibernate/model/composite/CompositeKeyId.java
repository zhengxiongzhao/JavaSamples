package com.jason.hibernate.model.composite;

import java.io.Serializable;

/**
 * ��������
 * 
 * @ClassName: CompositeKey
 * @Description: TODO()
 * @author Jason CIHUnKnown.tk
 * @date 2013-8-4 ����01:14:56
 */
//@Embeddable  //one
public class CompositeKeyId implements Serializable {
	private static final long serialVersionUID = -4003853020636053840L;
	//@Id   //Three
	private int id;
	//@Id   //Three
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