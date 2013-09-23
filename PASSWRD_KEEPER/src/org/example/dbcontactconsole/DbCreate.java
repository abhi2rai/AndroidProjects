package org.example.dbcontactconsole;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class DbCreate extends SQLiteOpenHelper{
	private static final String DB_NAME = "AccountTest.db";
	private static final int DB_VERSION = 1;
	
	/** Create a helper object for the Events database */
	   public DbCreate(Context context) { 
	      super(context, DB_NAME, null, DB_VERSION);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) { 
	      db.execSQL("CREATE TABLE " + DbConstants.TABLE_NAME1 + " " +
	      		"(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	      			  DbConstants.SUBJECT + " TEXT NOT NULL," + 
	      			  DbConstants.USERNAME + " TEXT NOT NULL," +
	      			  DbConstants.PASSWORD + " TEXT NOT NULL," +  
	      			  DbConstants.QUESTION + " Text, " + 
	      			  DbConstants.ANSWER + " Text, " + 
	      			  DbConstants.NOTES + " );");
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, 
	         int newVersion) {
	      db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_NAME1);
	      onCreate(db);
	   }
}
