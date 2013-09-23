package com.priority.planner;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Setpriority extends Activity implements OnClickListener {
	private Button yes;
	private Button no;
	static int SIMPLE_NOTIFICATION_ID=5;
	private LinearLayout layout;
	private Spinner spin1;
	private NotificationManager mnotify;
	private Button schedule;
	Double Time=(double) 50;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priority);
        
        mnotify=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        schedule=(Button)findViewById(R.id.button1);
        yes=(Button)findViewById(R.id.yes);
        no=(Button)findViewById(R.id.no);
        spin1=(Spinner)findViewById(R.id.spinner1);
        layout=(LinearLayout)findViewById(R.id.linearLayout2);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        schedule.setOnClickListener(this);
        layout.setVisibility(View.GONE);
        ArrayAdapter<CharSequence>ada=ArrayAdapter.createFromResource(this,R.array.prioritynum,android.R.layout.simple_spinner_item);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(ada);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getId();
		switch(id)
		{
		case R.id.yes:layout.setVisibility(View.VISIBLE);
						break;
		case R.id.button1:final Notification notifyDetails =new Notification(R.drawable.icon,"YOUR MEETING IS SCHEDULED",System.currentTimeMillis());
	    notifyDetails.ledARGB=0xff00ff00;
	    notifyDetails.ledOffMS=100;
	    notifyDetails.ledOnMS=300;
	    notifyDetails.flags |= Notification.FLAG_SHOW_LIGHTS;
		CharSequence contentTitle = "MEETING DETAILS";
		CharSequence contentText = "CLICK TO VIEW THE DETAILS";
		Intent notifyIntent = new Intent(Setpriority.this,MEETING.class);
		PendingIntent intent =PendingIntent.getActivity(this, 0,
		      notifyIntent,0);
		notifyDetails.setLatestEventInfo(Setpriority.this, contentTitle, contentText, intent);
		 mnotify.notify(SIMPLE_NOTIFICATION_ID,notifyDetails);
		 break;

		}
	}

}
