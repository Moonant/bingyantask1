package com.bingyan.bingyantask1;

import android.R.anim;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {
	public String[] values =new String[]{"所有公选课","所有学生","所有教师","添加"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , values);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch ((int)id) {
		case 0:
			intent.setClass(this, CourseListActivity.class);
			startActivity(intent);
			break;
		case 1:
			break;
		case 2:
			intent.setClass(this, TeacherListActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent.setClass(this, AddAvtivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		super.onListItemClick(l, v, position, id);
	}
	
	
	


}
