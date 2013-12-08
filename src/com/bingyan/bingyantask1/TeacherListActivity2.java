package com.bingyan.bingyantask1;

import java.text.SimpleDateFormat;

import com.bingyan.bingyantask1.db.TeacherDao;
import com.bingyan.bingyantask1.entry.Teacher;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class TeacherListActivity2 extends Activity {
	public TeacherDao teacherDao;
	public ListView mListView;
	public CursorAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView)findViewById(R.layout.teacher_item);
		teacherDao = new TeacherDao(this);
		Cursor cursor = teacherDao.query();
		mAdapter = new CursorAdapter(this,cursor) {
			public LayoutInflater mInflater;
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				// TODO Auto-generated method stub
				mInflater = getLayoutInflater();
				View view = mInflater.inflate(R.layout.teacher_item, null);
				Viewholder holder = new Viewholder();
				holder.nameTV = (TextView) view.findViewById(R.id.textView1);
				holder.ageTV = (TextView) view.findViewById(R.id.textView2);
				holder.contactTV = (TextView) view.findViewById(R.id.textView3);
				view.setTag(holder);
				return null;
			}
			
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				// TODO Auto-generated method stub
				Viewholder holder = (Viewholder)view.getTag();
				int age;
				if(cursor.getInt(cursor.getColumnIndex("birth_year"))>0){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
					int nowYear =Integer.parseInt(sdf.format(new java.util.Date()));
					age = nowYear - cursor.getInt(cursor.getColumnIndex("birth_year"));
				}else {
					age = 0;
				}
				holder.id = cursor.getLong(cursor.getColumnIndex("_id")); 
				holder.nameTV.setText(cursor.getString(cursor.getColumnIndex("name")));
				holder.ageTV.setText(String.valueOf(age));
				holder.contactTV.setText(cursor.getString(cursor.getColumnIndex("contact")));
			}
		};
		mListView.setAdapter(mAdapter);
//		mListView.setOnItemLongClickListener();
	}
	
	public class Viewholder{
		public long id;
		public TextView nameTV ;
		public TextView ageTV ;
		public TextView contactTV;
	}
	
//	private void initAdapter(Cursor cursor){
//	mAdapter = new CursorAdapter(this, cursor) {
//		public LayoutInflater mInflater;
//		@Override
//		public View newView(Context context, Cursor cursor, ViewGroup parent) {
//			// TODO Auto-generated method stub
//			//LayoutInflater.from(Context)
//			mInflater = getLayoutInflater();
//			
//			return null;
//		}
//		
//		@Override
//		public void bindView(View arg0, Context arg1, Cursor arg2) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
//}
}
