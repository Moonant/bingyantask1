package com.bingyan.bingyantask1;

import com.bingyan.bingyantask1.db.CommentDao;
import com.bingyan.bingyantask1.db.StudentDao;
import com.bingyan.bingyantask1.entry.Comment;
import com.bingyan.bingyantask1.entry.Course;
import com.bingyan.bingyantask1.entry.Student;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage.SubmitPdu;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteCommentActivity extends Activity implements OnClickListener{
	private Course mCourse;
	private Button sendBtn;
	private EditText stuNameET;
	private EditText contentET;
	private StudentDao studentDao;
	private CommentDao commentDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writecomment);
		mCourse =(Course) getIntent().getSerializableExtra("course");
		studentDao = new StudentDao(this);
		commentDao = new CommentDao(this);
		initView();
		sendBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sent_btn:
			sentComment();
			
			break;

		default:
			break;
		}
	}
	private void initView(){
		sendBtn = (Button) findViewById(R.id.sent_btn);
		contentET = (EditText) findViewById(R.id.content);
		stuNameET = (EditText) findViewById(R.id.stu_name);
	}
	public void sentComment(){
		Student student = null;
		String stuName = (String) stuNameET.getText().toString();
		String content = (String) contentET.getText().toString();
		if(stuName.trim().isEmpty()||content.trim().isEmpty()){
			Toast.makeText(this, "can't be empty", Toast.LENGTH_LONG).show();
			return;
		}
		student = studentDao.getStuByName(stuName);
		if(student==null){
			Log.i("myLog","student==null");
			student = studentDao.getStuById(studentDao.insertByName(stuName));
		}
		Comment comment = new Comment(mCourse, student, content, null);
		if(commentDao.insert(comment)>0){
			Toast.makeText(this, "insert Ok", Toast.LENGTH_LONG).show();
		}
	}
}
