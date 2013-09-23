package org.example.dbcontactconsole;

public final class DbConstants {
	public static final String TABLE_NAME1 = "MailAccount";
	public static final String TABLE_NAME2 = "BankAccount";
	public static final String TABLE_NAME3 = "CardAccount";

	//define database columns
	public static final String SUBJECT = "name";
	public static final String USERNAME = "phone";
	public static final String PASSWORD = "email";
	public static final String QUESTION ="ques";
	public static final String ANSWER = "ans";
	public static final String NOTES = "notes";
	
	//defining database columns for Bank table
	
	public static final String BANK_NAME = "bankname";
	public static final String ACCOUNT_NO = "Account_No";
	public static final String B_NOTES = "notes";
	public static final String PIN_NO = "pin_no";
	
	//defining database columns for card details
	
	public static final String BANK_CARD_NAME = "bankname";
	public static final String CARD_NO = "card_no";
	public static final String SECRET_PIN_NO  = "pin_no";
	public static final String C_NOTES = "card_notes";
	
	
	
	
	/**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	  */
	private DbConstants(){
		//this prevents even the native class from 
	    //calling this constructor as well :
	    throw new AssertionError();
	}

}
