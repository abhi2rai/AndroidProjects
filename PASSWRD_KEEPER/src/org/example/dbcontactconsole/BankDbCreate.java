
package org.example.dbcontactconsole;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class BankDbCreate extends SQLiteOpenHelper {
	private static final String DB_NAME = "BankAccount.db";
	private static final int DB_VERSION = 1;
	
	/** Create a helper object for the Events database */
	   public BankDbCreate(Context context) { 
	      super(context, DB_NAME, null, DB_VERSION);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db1) { 
	      db1.execSQL("CREATE TABLE " + DbConstants.TABLE_NAME2 + " " +
	      		"(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
	      			  DbConstants.BANK_NAME + " TEXT NOT NULL," + 
	      			  DbConstants.ACCOUNT_NO + " TEXT ," +
	      			  DbConstants.PIN_NO + " TEXT ," +  
	      			  DbConstants.B_NOTES + " );");
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db1, int oldVersion, 
	         int newVersion) {
	      db1.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_NAME2);
	      onCreate(db1);
	   }
}
