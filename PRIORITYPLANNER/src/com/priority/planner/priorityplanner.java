package com.priority.planner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class priorityplanner extends Activity {
	private ListView lv;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv=(ListView)findViewById(R.id.listView1);
		ArrayList<String>task=new ArrayList<String>();
		task.add("MEETING");
		task.add("TASK");
		ArrayAdapter<String>ada=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,task);
		lv.setAdapter(ada);
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2)
				{
				case 0:Intent meet=new Intent(priorityplanner.this,MEETING.class);
									startActivity(meet);
									break;
				case 1:Intent tas=new Intent(priorityplanner.this,TASK.class);
									startActivity(tas);
				}
				
				
			}
			
		});
	}
		

}
