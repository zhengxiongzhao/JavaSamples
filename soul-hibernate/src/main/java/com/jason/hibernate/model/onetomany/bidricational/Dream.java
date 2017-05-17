package com.jason.hibernate.model.onetomany.bidricational;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dream implements Serializable {
	private static final long serialVersionUID = 5528683744502620340L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="userId")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}
