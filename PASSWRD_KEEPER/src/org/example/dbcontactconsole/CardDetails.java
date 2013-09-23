
package org.example.dbcontactconsole;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CardDetails extends Activity{
	private Button cancelButton1;
	private Button saveContactButton1;
	
	private static EditText bc_nameField ; 
	private static EditText card_no_Field ;
	private static EditText pin_no_Field ;
	private static EditText b_notesField ;
	
	private static String b_operationType;
	static final int RESULT_MODIFY_USER1 = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_details);
		
		Bundle incomingActionType = getIntent().getExtras();
		b_operationType = incomingActionType.getString("mod_type");
		
		if (b_operationType.equals("addPerson1")){
			
			bc_nameField = (EditText)findViewById(R.id.bank_card_Name); 
			card_no_Field =(EditText)findViewById(R.id.card_no);
			pin_no_Field = (EditText)findViewById(R.id.secretPin_no);
			b_notesField = (EditText)findViewById(R.id.cardNotes);
			
		}
		
		else if (b_operationType.equals("modifyPerson1")){
			
			String modifyBankName = incomingActionType.getString("cBankName");
			bc_nameField = (EditText) findViewById(R.id.bank_card_Name);
			bc_nameField.setText(modifyBankName);
			
		    String modifyCard_No =incomingActionType.getString("cCard_No");
		    card_no_Field =(EditText)findViewById(R.id.card_no);
		    card_no_Field.setText(modifyCard_No);
			
			String modifyPin_no = incomingActionType.getString("cPin_no");
			pin_no_Field =(EditText)findViewById(R.id.secretPin_no);
			pin_no_Field.setText(modifyPin_no);
			
			String modifyNotes = incomingActionType.getString("cBNotes");
			b_notesField =(EditText)findViewById(R.id.cardNotes);
			b_notesField.setText(modifyNotes);
		}
					
	
	cancelButton1 = (Button) findViewById(R.id.cancelcardbutton);
	saveContactButton1 = (Button) findViewById(R.id.savecardbutton);

	saveContactButton1.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			Intent resultIntent = new Intent();
			Bundle contactData = new Bundle();

				if (bc_nameField.getText().toString().length() == 0){
					new AlertDialog.Builder(CardDetails.this).setTitle("Error").setMessage("Bankname field cannot be empty!").setNeutralButton("Close", null).show();
				}  else{
					contactData.putString("contactbankName", bc_nameField.getText().toString());
					contactData.putString("contactCard", card_no_Field.getText().toString());
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
