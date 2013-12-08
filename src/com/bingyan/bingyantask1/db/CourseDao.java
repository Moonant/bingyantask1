package com.bingyan.bingyantask1.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bingyan.bingyantask1.entry.Course;
import com.bingyan.bingyantask1.entry.Teacher;

public class CourseDao {
	public DBOpenhelper helper;
	public SQLiteDatabase db;
	public TeacherDao teacherDao;
	
	public CourseDao(Context context){
		helper = new DBOpenhelper(context);
		teacherDao = new TeacherDao(context);
	}
	
	private void close(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
	//插入 空字符 查找 返回空字符
	//不插入 查找 返回 null
	public long insert(Course course){
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", course.getName());
		values.put("time", course.getTime());
		values.put("place", course.getPlace());
		values.put("teacher_id", course.getTeacher().getId());
		values.put("intro", course.getIntroduce());
		rowId = db.insert(DBOpenhelper.COURSE_TBL, null, values);
		db.close();
		return rowId;
	}
	
	public ArrayList<Course> getAllCourses(){
		ArrayList<Course> courses = new ArrayList<Course>();
		Cursor cursor = query();
		while (cursor.moveToNext()) {
			//attention
			Teacher teacher = teacherDao.getTeacherById(cursor.getLong(cursor.getColumnIndex("teacher_id")));
			Log.i("myTag","CDtea_name="+teacher.getName());
			Course course = new Course(cursor.getLong(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("name")),
					teacher, 
					cursor.getString(cursor.getColumnIndex("time")), 
					cursor.getString(cursor.getColumnIndex("place")), 
					cursor.getString(cursor.getColumnIndex("intro")));
			courses.add(course);
		}
		cursor.close();
		close();
		return courses;
	}
	public Course getCourseByName(String name){
		Teacher teacher = null;
		Course course = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM "+DBOpenhelper.COURSE_TBL+" where name = ?", new String[]{name});
		if (cursor.moveToNext()) {
			teacher = teacherDao.getTeacherById(cursor.getLong(cursor.getColumnIndex("teacher_id")));
			course = new Course(cursor.getLong(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("name")),
					teacher, 
					cursor.getString(cursor.getColumnIndex("time")), 
					cursor.getString(cursor.getColumnIndex("place")), 
					cursor.getString(cursor.getColumnIndex("intro")));
			
		}
		cursor.close();
		db.close();
		return course;
	}
	
	public Cursor query(){
		Cursor cursor = null;
		db = helper.getReadableDatabase();
//		db.rawQuery("select c.*,t.name as teacher from ? as c inner join ? as t where t._id c.teacher_id ", 
//				String[]{DBOpenhelper.COURSE_TBL,DBOpenhelper.TEACHER_TBL});
		cursor = db.rawQuery("SELECT * FROM "+DBOpenhelper.COURSE_TBL, null);

		return cursor;
	}
	public int getCount(){
		int count = 0;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+DBOpenhelper.COURSE_TBL, null);
		if(cursor.moveToNext()){
			count = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return count;
	}
}
