package com.jason.hibernate.model.onetoone.primary;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 6908503740629000371L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer age;
	private double height;
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Dream dream;

	public Dream getDream() {
		return dream;
	}

	public Integer getAge() {
		return age;
	}

	public double getHeight() {
		return height;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setDream(Dream dream) {
		this.dream = dream;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
