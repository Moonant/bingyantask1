package com.bingyan.bingyantask1.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenhelper extends SQLiteOpenHelper {
	public static final String DBNAME = "elective.db";
	public static final int VERSION = 1;
	public static final String COURSE_TBL = "cource";
	public static final String TEACHER_TBL = "teacher";
	public static final String STUDENT_TBL = "student";
	public static final String STU_CRS_TBL = "stu_crs";
	public static final String COURSECOMMENT_TBL = "coursecomment";
	public static final String CRSSTAR_TBL = "crsstar";
	
	public DBOpenhelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DBNAME, null, VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS "+COURSE_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT ,"+
				"name text NOT NULL ,"+
				"teacher_id integer REFERENCE "+TEACHER_TBL+" _id,"+
				"time text,"+
				"place text,"+
				"intro text"+
				")"
				);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+TEACHER_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT,"+
				"name varchar(10) NOT NULL ,"+
				"birth_year integer ,"+
				"contact text"+
				")"
				);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+STUDENT_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT,"+
				"uid char(10) UNIQUE,"+
				"name varchar(10) NOT NULL"+
				")"							
				);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+STU_CRS_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT,"+
				"student_id integer REFERENCE "+STUDENT_TBL+" _id,"+
				"course_id integer REFERENCE "+COURSE_TBL+" _id,"+
				"score integer"+
				")"
				);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+COURSECOMMENT_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT,"+
				"student_id integer REFERENCE "+STUDENT_TBL+" _id,"+
				"course_id integer REFERENCE "+COURSE_TBL+" _id,"+
				"content text NOT NULL,"+
				"time timestamp DEFAULT (datetime('now','localtime'))"+
				")"
				);
		//create table tb3(idINTEGER PRIMARY KEY,timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, weight DOUBLE)"; 
		//String myDate =cursor.getString(cursor.getColumnIndex("datetime(timestamp,'localtime')"));  
		//SimpleDateFormat format = newSimpleDateFormat("yyyy-MM-dd HH:mm");  
		//Date date = format.parse(myDate)
		
		db.execSQL("CREATE TABLE IF NOT EXISTS "+CRSSTAR_TBL+
				"("+
				"_id integer PRIMARY KEY AUTOINCREMENT,"+
				"student_id integer REFERENCE "+STUDENT_TBL+" _id,"+
				"course_id integer REFERENCE "+COURSE_TBL+" _id,"+
				"star integer NOT NULL CHECK(star<6) default 0"+
				")"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
