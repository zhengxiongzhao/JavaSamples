package com.jason.hibernate.model.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="LockVersion_table",catalog="jasondata")
public class LockVersion implements Serializable {
	private static final long serialVersionUID = 7753913500072975287L;
	@Id
	private Integer Id;
	@Column
	private String name;
	@Version
	@Column(name = "OPTLOCK")
	private Integer version;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
