package com.bingyan.bingyantask1.entry;

public class Comment {
	private Course course;
	private Student stu;
	private String content;
	private String time;

	public Comment(Course course, Student stu, String content, String time) {
		// TODO Auto-generated constructor stub
		this.course = course;
		this.stu = stu;
		this.content = content;
		this.time = time;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
