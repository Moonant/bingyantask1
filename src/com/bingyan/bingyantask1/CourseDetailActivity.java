package com.bingyan.bingyantask1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bingyan.bingyantask1.db.CommentDao;
import com.bingyan.bingyantask1.entry.Comment;
import com.bingyan.bingyantask1.entry.Course;
import com.bingyan.bingyantask1.entry.Teacher;

public class CourseDetailActivity extends Activity implements OnClickListener {
	private Course mCourse;
	private Teacher teacher;
	private TextView cNameTV;
	private TextView cTimeTV;
	private TextView cPlaceTV;
	private TextView cTeacherTV;
	private TextView cIntroTV;
	private Button commentBtn;
	private ArrayList<Comment> mComments;
	private CommentDao commentDao; 
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_detail);
		mCourse = (Course) getIntent().getSerializableExtra("course");
		teacher = mCourse.getTeacher();
		Log.i("myTag","teacher_id="+teacher.getId());
		Log.i("myTag","tea_name="+teacher.getName());
		initView();
		commentDao = new CommentDao(this);
		commentBtn.setOnClickListener(this);
		mComments = commentDao.getAllComments(mCourse);
		MyAdapter mAdapter = new MyAdapter(this);
		mListView.setAdapter(mAdapter);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.comment_btn:
			Intent intent = new Intent(this, WriteCommentActivity.class);
//			intent.putExtra("courseId", mCourse.getId());
//			startActivityForResult(intent, requestCode);
			Bundle bundle = new Bundle();
			bundle.putSerializable("course", mCourse);
			intent.putExtras(bundle);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	private void initView(){
		mListView = (ListView) findViewById(R.id.listView1);
		cNameTV = (TextView) findViewById(R.id.c_name);
		cTimeTV = (TextView) findViewById(R.id.c_time);
		cPlaceTV = (TextView) findViewById(R.id.c_place);
		cIntroTV = (TextView) findViewById(R.id.c_intro);
		cTeacherTV = (TextView) findViewById(R.id.c_teacher_name);
		commentBtn = (Button) findViewById(R.id.comment_btn);
		cNameTV.setText(mCourse.getName());
		cTeacherTV.setText("教师："+(teacher.getId()<0 ? "未知": teacher.getName()));
		cTimeTV.setText("时间："+(mCourse.getTime().trim().isEmpty() ? "未知":mCourse.getTime()));
		cPlaceTV.setText("地点："+(mCourse.getPlace().trim().isEmpty() ? "未知":mCourse.getPlace()));
		cIntroTV.setText("asas");
		if(mCourse.getPlace()==null){
			Log.i("mytag","mCourse.getPlace()==null");
		}
		if(mCourse.getTime()==null){
			Log.i("mytag","mCourse.getTime()==null");
		}
		if(mCourse.getIntroduce()==null){
			Log.i("mytag","mCourse.getIntroduce()==null");
		}
		mCourse.getIntroduce().trim().isEmpty();
		cIntroTV.setText("介绍："+(mCourse.getIntroduce().trim().isEmpty() ? "未知":mCourse.getIntroduce()));
	}
	private ArrayList<Comment> getData(){
		return mComments;
	}
	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return getData().size();
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			//观察convertView随ListView滚动情况   
			Log.v("MyListViewBase", "getView " + position + " " + convertView);
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.comment_list_item, null);
				holder = new ViewHolder();
				holder.content = (TextView) convertView.findViewById(R.id.content);
				holder.stuName = (TextView) convertView.findViewById(R.id.stu_name);
				holder.time = (TextView) convertView.findViewById(R.id.time);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.content.setText(getData().get(position).getContent());
			holder.stuName.setText(getData().get(position).getStu().getName());
			holder.time.setText(getData().get(position).getTime());
			return convertView;
		}
		
		public final class ViewHolder{
			public TextView content;
			public TextView stuName;
			public TextView time;
		}
	}  
	
}
