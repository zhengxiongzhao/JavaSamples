package com.jason.hibernate.model.composition;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * ���ӳ��
 * 
 * @ClassName: User
 * @Description: TODO()
 * @author Jason CIHUnKnown.tk
 * @date 2013-8-5 ����01:04:58
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -8407942503884326085L;
	@Id
	private Integer id;
	private String name;
	private Integer age;
	private double height;
	@Embedded
	private Dream dream;

	public Dream getDream() {
		return dream;
	}

	public void setDream(Dream dream) {
		this.dream = dream;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
