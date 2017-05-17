package com.jason.hibernate.model.manytomany.bidirectional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Dream implements Serializable {
	private static final long serialVersionUID = 5528683744502620340L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy="dreams")
	private Set<User> users=new HashSet<User>();

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
