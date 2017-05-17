package com.jason.hibernate.model.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Province implements Serializable {
	private static final long serialVersionUID = 6216440656387159381L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	private Set<Province> children = new HashSet<Province>();

	@ManyToOne
	@JoinColumn(name = "parentId")
	private Province parent;

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

	public Set<Province> getChildren() {
		return children;
	}

	public void setChildren(Set<Province> children) {
		this.children = children;
	}

	public Province getParent() {
		return parent;
	}

	public void setParent(Province parent) {
		this.parent = parent;
	}

}
