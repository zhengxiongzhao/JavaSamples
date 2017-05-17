package com.jason.hibernate.model.onetomany.unidricational;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
public class User implements Serializable {
	private static final long serialVersionUID = 6908503740629000371L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer age;
	private double height;
	@Temporal(TemporalType.DATE)
	private Date bithday;

	@OneToMany
	@JoinColumn(name="dreamId")
	private Set<Dream> dreams = new HashSet<Dream>();

	public Set<Dream> getDreams() {
		return dreams;
	}

	public void setDreams(Set<Dream> dreams) {
		this.dreams = dreams;
	}

	public Date getBithday() {
		return bithday;
	}

	public void setBithday(Date bithday) {
		this.bithday = bithday;
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
