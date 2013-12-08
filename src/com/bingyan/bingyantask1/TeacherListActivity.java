package com.bingyan.bingyantask1;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.bingyan.bingyantask1.db.TeacherDao;
import com.bingyan.bingyantask1.entry.Teacher;

public class TeacherListActivity extends ListActivity implements
		 OnItemLongClickListener {
	private ArrayList<Teacher> teachers;
	private TeacherDao teacherDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		teacherDao = new TeacherDao(this);
		teachers = teacherDao.getAllTeachers();
		MyAdapter myAdapter = new MyAdapter(this);
		setListAdapter(myAdapter);
		getListView().setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.i("mytag","vvvv"+position+id);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View view = mInflater.inflate(R.layout.three_edittext, null);
		final Teacher teacher = teachers.get(position);
		final EditText nameET = ((EditText)view.findViewById(R.id.editText1));
		nameET.setText(teacher.getName());
		final EditText ageET = ((EditText)view.findViewById(R.id.editText2));
		ageET.setText(teacher.getAge() == 0 ? "":String.valueOf(teacher.getAge()));
		final EditText contactET = ((EditText)view.findViewById(R.id.editText3));
		contactET.setText(teacher.getContact()==null ? "":teacher.getContact());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("修改");
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//内部类获取外面的数据
				teacher.setAge(Integer.parseInt(ageET.getText().toString()));
				teacher.setName(nameET.getText().toString());
				teacher.setContact(contactET.getText().toString());
				teacherDao.update(teacher);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.create().show();
		return false;
	}
	//CursorAdapter
//	private void initAdapter(Cursor cursor){
//		mAdapter = new CursorAdapter(this, cursor) {
//			public LayoutInflater mInflater;
//			@Override
//			public View newView(Context context, Cursor cursor, ViewGroup parent) {
//				// TODO Auto-generated method stub
//				//LayoutInflater.from(Context)
//				mInflater = getLayoutInflater();
//				
//				return null;
//			}
//			
//			@Override
//			public void bindView(View arg0, Context arg1, Cursor arg2) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//	}

	
//	BaseAdapter
	private class MyAdapter extends BaseAdapter{
		public LayoutInflater mInflater;
		
		public MyAdapter(Context context) {
			// TODO Auto-generated constructor stub
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return teachers.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//position id index 从0还是1开始
			convertView = mInflater.inflate(R.layout.teacher_item, null);
			TextView nameTV = (TextView) convertView.findViewById(R.id.textView1);
			TextView ageTV = (TextView) convertView.findViewById(R.id.textView2);
			TextView contactTV = (TextView) convertView.findViewById(R.id.textView3);
			Teacher teacher = teachers.get(position);
			nameTV.setText(teacher.getName());
			ageTV.setText(teacher.getAge() == 0 ? "未知":String.valueOf(teacher.getAge()));
			contactTV.setText(teacher.getContact()==null ? "未知":teacher.getContact());
			return convertView;
		}
	} 
}
