package com.bingyan.bingyantask1.db;

import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bingyan.bingyantask1.entry.Teacher;



//sqlite 插入带‘的数据，android：id和+id区别，

public class TeacherDao {
	public DBOpenhelper helper;
	public SQLiteDatabase db;
	
	public TeacherDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenhelper(context);
	}
	
	public void Close(){
		if(db!=null&&db.isOpen()){
			db.close();
		}
	}
	public Teacher getTeacherByName(String name){
		int birthYear = 0;
		Teacher teacher = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.TEACHER_TBL+" where name = ?;", new String[]{name});
		if(cursor.moveToNext()){
			if(cursor.getInt(cursor.getColumnIndex("birth_year"))>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
				int nowYear =Integer.parseInt(sdf.format(new java.util.Date()));
				birthYear = nowYear - cursor.getInt(cursor.getColumnIndex("birth_Year"));
			}
			teacher = new Teacher(cursor.getLong(cursor.getColumnIndex("_id")), 
					cursor.getString(cursor.getColumnIndex("name")),
					birthYear, 
					cursor.getString(cursor.getColumnIndex("contact")));
		}
		cursor.close();
		db.close();
		return teacher;
	}
	public Teacher getTeacherById(long id){
		int age = 0;
		Teacher teacher = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.TEACHER_TBL+" where _id = ?;", new String[]{String.valueOf(id)});
		if(cursor.moveToNext()){
			if(cursor.getInt(cursor.getColumnIndex("birth_year"))>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
				int nowYear =Integer.parseInt(sdf.format(new java.util.Date()));
				age = nowYear - cursor.getInt(cursor.getColumnIndex("birth_year"));
			}
			teacher = new Teacher(cursor.getLong(cursor.getColumnIndex("_id")), 
					cursor.getString(cursor.getColumnIndex("name")),
					age, 
					cursor.getString(cursor.getColumnIndex("contact")));
		}else{
			teacher = new Teacher(-1L, "",0 , "");
		}
		Log.i("myTag","TDtea_name="+teacher.getName());
		cursor.close();
		db.close();
		return teacher;
	}
	public long insertByName(String name){
		long rawId = -1;
		if(!name.trim().isEmpty()){
			Log.i("muLog","1"+name+"1");
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", name);
			rawId = db.insert(DBOpenhelper.TEACHER_TBL, null, values);
			db.close();
		}		
		return rawId;
	}
	public Cursor query(){
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.TEACHER_TBL, null);
		return cursor;
	}
	
	public ArrayList<Teacher> getAllTeachers(){
		int age = 0;
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.TEACHER_TBL, null);
		while (cursor.moveToNext()) {
			Log.i("myTag","birth_year"+cursor.getInt(cursor.getColumnIndex("birth_year")));
			if(cursor.getInt(cursor.getColumnIndex("birth_year"))>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
				int nowYear =Integer.parseInt(sdf.format(new java.util.Date()));
				age = nowYear - cursor.getInt(cursor.getColumnIndex("birth_year"));
			}else {
				age = 0;
			}
			Teacher teacher = new Teacher(cursor.getLong(cursor.getColumnIndex("_id")), 
					cursor.getString(cursor.getColumnIndex("name")),
					age, 
					cursor.getString(cursor.getColumnIndex("contact")));
			teachers.add(teacher);			
		}
		cursor.close();
		db.close();
		return teachers;
	}
	public int update(Teacher teacher){
		int rawNum = 0;
		if(!teacher.getName().trim().isEmpty()){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", teacher.getName());
			if(teacher.getAge()>0&&teacher.getAge()<100){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
				int nowYear =Integer.parseInt(sdf.format(new java.util.Date()));
				int birth_year = nowYear - teacher.getAge();
				values.put("birth_year", birth_year);
			}
			if(teacher.getContact()!=null&&!teacher.getContact().trim().isEmpty()){
				values.put("contact", teacher.getContact());
			}			
			rawNum = db.update(DBOpenhelper.TEACHER_TBL, values, "_id = ?", new String[]{String.valueOf(teacher.getId())});
		}
		return rawNum;
	}
}
