
package org.example.dbcontactconsole;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BankDetails extends Activity{
	private Button cancelButton1;
	private Button saveContactButton1;
	
	private static EditText b_nameField ; 
	private static EditText acc_no_Field ;
	private static EditText pin_no_Field ;
	private static EditText b_notesField ;
	
	private static String b_operationType;
	static final int RESULT_MODIFY_USER1 = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank_details);
		
		Bundle incomingActionType = getIntent().getExtras();
		b_operationType = incomingActionType.getString("mod_type");
		
		if (b_operationType.equals("addPerson1")){
			
			b_nameField = (EditText)findViewById(R.id.bankcontactName); 
			acc_no_Field =(EditText)findViewById(R.id.bankcontactAcc_no);
			pin_no_Field = (EditText)findViewById(R.id.bankcontactPin_no);
			b_notesField = (EditText)findViewById(R.id.bankcontactNotes);
			
		}
		
		else if (b_operationType.equals("modifyPerson1")){
			
			String modifyBankName = incomingActionType.getString("cBankName");
			b_nameField = (EditText) findViewById(R.id.bankcontactName);
			b_nameField.setText(modifyBankName);
			
		    String modifyAcc_No =incomingActionType.getString("cAccount_No");
		    acc_no_Field =(EditText)findViewById(R.id.bankcontactAcc_no);
		    acc_no_Field.setText(modifyAcc_No);
			
			String modifyPin_no = incomingActionType.getString("cPin_no");
			pin_no_Field =(EditText)findViewById(R.id.bankcontactPin_no);
			pin_no_Field.setText(modifyPin_no);
			
			String modifyNotes = incomingActionType.getString("cBNotes");
			b_notesField =(EditText)findViewById(R.id.bankcontactNotes);
			b_notesField.setText(modifyNotes);
		}
					
	
	cancelButton1 = (Button) findViewById(R.id.cancelbankbutton);
	saveContactButton1 = (Button) findViewById(R.id.savebankbutton);

	saveContactButton1.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			Intent resultIntent = new Intent();
			Bundle contactData = new Bundle();

				if (b_nameField.getText().toString().length() == 0){
					new AlertDialog.Builder(BankDetails.this).setTitle("Error").setMessage("Bankname field cannot be empty!").setNeutralButton("Close", null).show();
				}  else{
					contactData.putString("contactbankName", b_nameField.getText().toString());
					contactData.putString("contactAccount", acc_no_Field.getText().toString());
		            contactData.putString("contactPin", pin_no_Field.getText().toString());
		            contactData.putString("contactNotes",b_notesField.getText().toString());
					resultIntent.putExtra("accountData", contactData);
					
					if (b_operationType.equals("addPerson1")){
						setResult(RESULT_FIRST_USER, resultIntent);
					}else if (b_operationType.equals("modifyPerson1")){
						setResult(RESULT_MODIFY_USER1, resultIntent);
					}				
					finish();
				}
		}
	});
	
	cancelButton1.setOnClickListener(new View.OnClickListener(){
		

		public void onClick(View v) {
			Intent resultIntent = new Intent();
			setResult(RESULT_CANCELED, resultIntent);
            finish();
			
			
		}
	});

	}
}
