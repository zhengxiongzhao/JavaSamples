package com.jason.hibernate.model.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
�������ע��Ϊ@Embeddable,�������������ע��Ϊ@Id
�����������ע��Ϊ@EmbeddedId
����ע��Ϊ@IdClass,������ʵ��������������������Զ�ע��Ϊ@Id)
* @ClassName: CompositeKey
* @Description: TODO()
* @author Jason CIHUnKnown.tk
* @date 2013-8-5 ����12:06:39 
*/
@Entity
@Table(name = "CompositeKey", catalog = "jasondata")
@IdClass(CompositeKeyId.class) //one
public class CompositeKey implements Serializable {
	private static final long serialVersionUID = 3322401354731923960L;
	//@Id     //two
	//@EmbeddedId //three
	//two   //three
	//private MyCompositeKeyId myCompositeKeyId;
	@Column
	private int age;
	@Column
	private String description;
	
	//two
	//three
	/*public MyCompositeKeyId getMyCompositeKeyId() {
		return myCompositeKeyId;
	}

	public void setMyCompositeKeyId(MyCompositeKeyId myCompositeKeyId) {
		this.myCompositeKeyId = myCompositeKeyId;
	}*/

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	
	
	
	
	
	//one
	@Id
	private int id;
	@Id
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
