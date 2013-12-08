package com.bingyan.bingyantask1.entry;

import java.io.Serializable;

public class Teacher implements Serializable {
	private long id;
	private String name;
	private int age;
	private String contact;
	
	//bug1
	public Teacher(long id, String name ,int age,String contact) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.age = age;
		this.contact = contact;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
