package com.bingyan.bingyantask1.entry;

import java.io.Serializable;

public class Course implements Serializable {
	private long id;
	private String name;
	private Teacher teacher;
	private String time;
	private String place;
	private String introduce;
	
	public Course(long id, String name, Teacher teacher, String time, String place, String introduce) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.time = time;
		this.place = place;
		this.introduce = introduce;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
