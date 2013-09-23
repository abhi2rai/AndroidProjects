

package org.example.dbcontactconsole;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View;
import android.app.ListActivity;

import static android.provider.BaseColumns._ID;

public class BankDbContactConsole extends ListActivity {
	private static String[] FROM1 = { _ID, DbConstants.BANK_NAME, DbConstants.ACCOUNT_NO, DbConstants.PIN_NO, DbConstants.B_NOTES,  };
	   private BankDbCreate contacts1;
	   private static SQLiteDatabase db1;
	   private static int[] TO1 = { R.id.rowid, R.id.b_name, R.id.Acc_no, R.id.Pin_no, R.id.b_notes };
	   private ListView lv1;
	   private static String itemId;
	   private Cursor cursor1;
	   
	   static final int CONTACT_CANCELED1 = 0;
	   static final int CONTACT_ADDED1 = 1;
	   static final int CONTACT_MODIFIED1 = 2;

	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main); 
	      showDatabaseContent1();
	      lv1 = getListView();
	      
	      lv1.setTextFilterEnabled(true);

	       lv1.setOnItemClickListener(new OnItemClickListener() {

	       public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
	    	   cursor1 = (Cursor) a.getItemAtPosition(position);
	    	   itemId = cursor1.getString(0);
	    	   openOptionsMenu();
	       	   }
	       });
	   
	       lv1.setOnItemSelectedListener(new OnItemSelectedListener() {
	    			
	    	   public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
	    		
	    	}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    		   
	   	   });
	   }
	   
	   //selected item index from ListView
	   public void showDialogItemId(long itemId){
		   Toast.makeText(this, "Menu item selected index is" + Long.toString(itemId), Toast.LENGTH_LONG).show();
	   }
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu){
		   MenuInflater inflater = getMenuInflater();
		   inflater.inflate(R.menu.menu, menu);
		   return true;
	   }
	   
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item){
		   switch (item.getItemId()){
		       case R.id.modifyitem:
		    	   if(null != itemId){
		    		   Bundle contactToModify1 = new Bundle();
		    		   contactToModify1.putString("cBankName", cursor1.getString(1));
		    		   contactToModify1.putString("cAccount_No", cursor1.getString(2));
		    		   contactToModify1.putString("cPin_no", cursor1.getString(3));
		    		   contactToModify1.putString("cBNotes", cursor1.getString(4));
		    		   
		    		   contactToModify1.putString("mod_type", "modifyPerson1");
		    		   Intent intent = new Intent(this, BankDetails.class);
		    		   intent.setClass(this, BankDetails.class);
		    		   intent.putExtras(contactToModify1);
		    		   startActivityForResult(intent, CONTACT_MODIFIED1);
		    	   }else{
		    		   Toast.makeText(this, "Select Account to modify", Toast.LENGTH_LONG).show();
		    	   }
		    	   break;
		    	   
		       case R.id.additem:
		    	   Intent i1 = new Intent(this, BankDetails.class);
		    	   Bundle bun1 = new Bundle();
		    	   bun1.putString("mod_type", "addPerson1");
		    	   i1.setClass(this, BankDetails.class);
		    	   i1.putExtras(bun1);
		    	   startActivityForResult(i1, CONTACT_ADDED1);
		    	   break;
		    	   		    	   
		       case R.id.removeitem:
		    	   if(null != itemId){
		    		   removeContact(itemId);
		    		   showDatabaseContent1();
		    	   }
		    	   else{
		    		   Toast.makeText(this, "Select Account to delete", Toast.LENGTH_LONG).show();
		    	   }
		    	   break;
		   }
		   return true;
	   }
	   
	   @Override
	   protected void onActivityResult(int requestCode, int resultCode, Intent intent){
	       // See which child activity is calling us back.
	       switch (resultCode) {
	           case CONTACT_ADDED1:
	               // This is the standard resultCode that is sent back if the
	               // activity crashed or didn't doesn't supply an explicit result.
	               if (resultCode == RESULT_FIRST_USER){
	            	   Bundle bundle = new Bundle();
	            	   bundle = intent.getBundleExtra("accountData");
	            	   addContact(bundle);
	    	    	   showDatabaseContent1();
	               } 
	               else{
	            	   Toast.makeText(this, "CANCEL ACCOUNT BUTTON PRESSED", Toast.LENGTH_LONG).show();
	               }
	               break;
	           case CONTACT_MODIFIED1:
	        	   if (resultCode == 2){
	        		   Bundle bundle = new Bundle();
	            	   bundle = intent.getBundleExtra("accountData");
	        		   modifyContact(bundle);
	        		   showDatabaseContent1();
	        	   }
	        	   else{
	        		   Toast.makeText(this, "MODIFY ACCOUNT FAILED", Toast.LENGTH_LONG).show();
	        	   }
	        	   break;
	           default:
	               break;
	       }
	   }
	   
	 //method removes item from database
	   private void removeContact(String itemId){
		   db1 = contacts1.getWritableDatabase();
    	   db1.delete(DbConstants.TABLE_NAME2, " _ID= " + itemId, null);

	   }

	   private void addContact(Bundle bundle) {
		      // Insert a new record into the Events data source.
		      // You would do something similar for delete and update.
		      db1 = contacts1.getWritableDatabase();
		      ContentValues vals = new ContentValues();
		      vals.put(DbConstants.BANK_NAME, bundle.getString("contactbankName"));
			  vals.put(DbConstants.ACCOUNT_NO, bundle.getString("contactAccount"));
			  vals.put(DbConstants.PIN_NO, bundle.getString("contactPin"));
			  vals.put(DbConstants.B_NOTES, bundle.getString("contactNotes"));
			  
			  db1.insertOrThrow(DbConstants.TABLE_NAME2, null, vals);
		   }
	   
	 //method should modify existing Contact
	   private void modifyContact(Bundle bundle){
		   db1 = contacts1.getWritableDatabase();
		   ContentValues vals = new ContentValues();
		   
		      vals.put(DbConstants.BANK_NAME, bundle.getString("contactbankName"));
		      vals.put(DbConstants.ACCOUNT_NO, bundle.getString("contactAccount"));
			  vals.put(DbConstants.PIN_NO, bundle.getString("contactPin"));
			  vals.put(DbConstants.B_NOTES, bundle.getString("contactNotes"));
			  
		   db1.update(DbConstants.TABLE_NAME2, vals, _ID + " = " + itemId, null);
	   }

	   private Cursor getContacts() {
		      db1 = contacts1.getReadableDatabase();
		      cursor1 = db1.query(DbConstants.TABLE_NAME2, FROM1, null, null, null, null, null);
		      startManagingCursor(cursor1);
		      return cursor1;
	   }
	   
	   public void showDatabaseContent1(){
		   contacts1 = new BankDbCreate(this); 
		   try {
		       cursor1 = getContacts(); 
		       showContacts(cursor1); 
		   } finally {
			   contacts1.close(); 
			   db1.close();
		   }
	   }

	   private void showContacts(Cursor cursor) {
	       //set up data binding
		   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.bank_item, cursor, FROM1, TO1);
		   setListAdapter(adapter);
	   }
}