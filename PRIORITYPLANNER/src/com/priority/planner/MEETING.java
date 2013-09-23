package com.priority.planner;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MEETING extends Activity implements OnClickListener{
	private Button pickd;
	private EditText title;
	private Button schedule;
	private TextView mshow;
	private TextView tshow;
	private int year;
	private int month;
	private int date;
	private int hours;
	private int min;
	private Button timer;
	static final int DATE_DIALOG_ID=0;
	static final int TIME_DIALOG_ID=1;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting);
        title=(EditText)findViewById(R.id.editText1);
        pickd=(Button)findViewById(R.id.pickdate);
        schedule=(Button)findViewById(R.id.schedule);
        tshow=(TextView)findViewById(R.id.showtime);
        timer=(Button)findViewById(R.id.time);
        mshow=(TextView)findViewById(R.id.mdateshow);
        
        schedule.setOnClickListener(this);
        timer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
				
			}
		});
        
        pickd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
				
			}
		});
        final Calendar c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        date=c.get(Calendar.DAY_OF_MONTH);
        hours=c.get(Calendar.HOUR_OF_DAY);
        min=c.get(Calendar.MINUTE);
        updatedisplay();
	}
	private void updatedisplay()
	{
		mshow.setText(new StringBuilder()
        .append(month + 1).append("-")
        .append(date).append("-")
        .append(year).append(" "));
		tshow.setText(new StringBuilder()
		.append(hours+1).append(":")
		.append(min+1).append(" "));

	}
      private DatePickerDialog.OnDateSetListener mdatesetlistener=new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			year=arg1;
			month=arg2;
			date=arg3;
			updatedisplay();
			
		}
	};
	private TimePickerDialog.OnTimeSetListener mtimesetlistener=new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
			hours=arg1;
			min=arg2;
			updatedisplay();
		}
	};
	protected Dialog onCreateDialog(int id)
	{
		switch (id) {
		case DATE_DIALOG_ID:return new DatePickerDialog(this,mdatesetlistener,year,month,date);
		case TIME_DIALOG_ID:return new TimePickerDialog(this,mtimesetlistener , hours, min,true);
		}
		return(null);
	}
	@Override
	public void onClick(View v) {
		int a=v.getId();
		switch(a)
		{
		case R.id.schedule:if(title.getText().toString().equals(""))
							{
								Toast.makeText(this,"PLEASE ENTER THE TEXT IN FIELDS",Toast.LENGTH_LONG).show();
							}
							else
							{
								Intent prior=new Intent(MEETING.this,Setpriority.class);
								startActivity(prior);
							}
		}
		
	}
}
