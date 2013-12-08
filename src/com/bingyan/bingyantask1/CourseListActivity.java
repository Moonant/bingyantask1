package com.bingyan.bingyantask1;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bingyan.bingyantask1.db.CourseDao;
import com.bingyan.bingyantask1.entry.Course;

public class CourseListActivity extends ListActivity {
	private CourseDao courseDao;
	ArrayList<Course> courses;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		courseDao = new CourseDao(this);
		courses = courseDao.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<String>();
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()){
			courseNames.add(iterator.next().getName());
		}		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Course course = courses.get((int)id);
		Intent intent = new Intent(this, CourseDetailActivity.class);
		//intent 传递对象
		Bundle bundle = new Bundle();
		bundle.putSerializable("course", course);
		intent.putExtras(bundle);
		startActivity(intent);
		String name = courses.get((int)id).getName();
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
	}
}
