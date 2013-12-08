package com.bingyan.bingyantask1.db;

import java.util.ArrayList;

import com.bingyan.bingyantask1.entry.Comment;
import com.bingyan.bingyantask1.entry.Course;
import com.bingyan.bingyantask1.entry.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CommentDao {
	public DBOpenhelper helper;
	public SQLiteDatabase db;
	public StudentDao studentDao;
	
	public CommentDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenhelper(context);
		studentDao = new StudentDao(context);
	}
	
	private void close(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
	public long insert(Comment comment){
		long rawId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("content", comment.getContent());
		values.put("course_id", comment.getCourse().getId());
		values.put("student_id", comment.getStu().getId());
		rawId = db.insert(DBOpenhelper.COURSECOMMENT_TBL, null, values);
		db.close();
		return rawId;
	}
	public Cursor query(long courseId){
		Cursor cursor = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM "+DBOpenhelper.COURSECOMMENT_TBL+" where course_id = ? order by time desc;", new String[]{String.valueOf(courseId)});
		return cursor;
	}
	public ArrayList<Comment> getAllComments(Course course){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		long courseId = course.getId();
		Cursor cursor = query(courseId);
		while(cursor.moveToNext()){
			Student student = studentDao.getStuById(cursor.getLong(cursor.getColumnIndex("student_id")));
			Comment comment = new Comment(course, student, 
					cursor.getString(cursor.getColumnIndex("content")), 
					cursor.getString(cursor.getColumnIndex("time")));
			comments.add(comment);
		}
		cursor.close();
		close();
		return comments;
	}
}
