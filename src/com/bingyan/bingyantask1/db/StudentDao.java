package com.bingyan.bingyantask1.db;

import com.bingyan.bingyantask1.entry.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StudentDao {
	private SQLiteDatabase db;
	private DBOpenhelper helper;
	
	public StudentDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenhelper(context);
	}
	public Student getStuById(long id){
		Student student = null;
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.STUDENT_TBL+ " where _id = ?;", new String[]{String.valueOf(id)});
		if(cursor.moveToNext()){
			student = new Student(cursor.getLong(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("uid")), 
					cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		db.close();
		return student;
	}
	public Student getStuByName(String name){
		Student student = null;
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from "+DBOpenhelper.STUDENT_TBL+ " where name = ?;", new String[]{name});
		if(cursor.moveToNext()){
			student = new Student(cursor.getLong(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("uid")), 
					cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		db.close();
		return student;
	}
	public long insertByName(String name){
		long rawId = -1;
		if(!name.trim().isEmpty()){
			Log.i("muLog","1"+name+"1");
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", name);
			rawId = db.insert(DBOpenhelper.STUDENT_TBL, null, values);
			db.close();
		}		
		return rawId;
	}
}
