package com.bingyan.bingyantask1.entry;

public class Student {
	private long id;
	private String uid;
	private String name;
	
	public Student(long id , String uid, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.uid = uid;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
