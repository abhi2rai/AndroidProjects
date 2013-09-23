package org.example.dbcontactconsole;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends ListActivity {

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		
		String[] main_options = getResources().getStringArray(R.array.pages_array);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item ,main_options)); 
		ListView lv = getListView(); 
		
		lv.setTextFilterEnabled(true); 
		lv.setOnItemClickListener(new OnItemClickListener() {   
	    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {    
				
	    
	    	 Intent myIntent = null;
	    	    
	    	   if(((TextView) view).getText().equals("Mail Accounts")){
	    	    myIntent = new Intent(view.getContext(), DbContactConsole.class);
	    	   }
	    	   if(((TextView) view).getText().equals("Bank Accounts")){
		    	    myIntent = new Intent(view.getContext(), BankDbContactConsole.class);
		    	   }
	    	   if(((TextView) view).getText().equals("Card Details")){
		    	    myIntent = new Intent(view.getContext(), CardDbContactConsole.class);
		    	   }
	    	
	    	   startActivity(myIntent);

	    }
	   });


	}
}
	