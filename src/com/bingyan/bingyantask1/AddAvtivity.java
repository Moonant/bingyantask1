package com.bingyan.bingyantask1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bingyan.bingyantask1.db.CourseDao;
import com.bingyan.bingyantask1.db.TeacherDao;
import com.bingyan.bingyantask1.entry.Course;
import com.bingyan.bingyantask1.entry.Teacher;

public class AddAvtivity extends Activity implements OnClickListener{
	private EditText courseNameET;
	private EditText timeET;
	private EditText placeET;
	private EditText teacherET;
	private EditText introET;
	private Button addCourseBtn;
	private TeacherDao teacherDao;
	private CourseDao courseDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		teacherDao = new TeacherDao(this);
		courseDao = new CourseDao(this);
		initView();
		addCourseBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		switch (id) {
		case R.id.addCourse:
			addCourse();
			break;

		default:
			break;
		}
	}
	
	private void initView(){
		courseNameET = (EditText)findViewById(R.id.editName);
		timeET = (EditText)findViewById(R.id.editTime);
		placeET = (EditText)findViewById(R.id.editPlace);
		teacherET = (EditText)findViewById(R.id.editTeacher);
		introET = (EditText)findViewById(R.id.editIntro);
		addCourseBtn = (Button)findViewById(R.id.addCourse);	
	}
	
	private void addCourse(){
		Teacher teacher = null;
		Course nCourse = null;
		String name = courseNameET.getText().toString();
		String time = timeET.getText().toString();
		String place = placeET.getText().toString();
		String introduce = introET.getText().toString();
		String teacherName = teacherET.getText().toString();
		if(name.trim().isEmpty()){
			Toast.makeText(this, "CourseName can't be empty", Toast.LENGTH_LONG).show();
			return;
		}
		if(courseDao.getCourseByName(name)!=null){
			Toast.makeText(this, "CourseName alerdy exist", Toast.LENGTH_LONG).show();
			return;
		}
		teacher = teacherDao.getTeacherByName(teacherName);
		if(teacher==null){
			Log.i("myLog","teacher==null");
			teacher = teacherDao.getTeacherById(teacherDao.insertByName(teacherName));
		}
		nCourse = new Course(0, name, teacher, time, place, introduce);
		if(courseDao.insert(nCourse)>0){
			Toast.makeText(this, "insert Ok", Toast.LENGTH_LONG).show();
		}
	}
}
