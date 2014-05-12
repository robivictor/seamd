package com.indigitous.seamd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	Button signUp_bt;
	EditText name_et;
	EditText email_et;
	EditText phoneNo_et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		signUp_bt = (Button) findViewById(R.id.button_signup);
		name_et = (EditText) findViewById(R.id.EditText_name);
		email_et = (EditText) findViewById(R.id.editText_email);
		phoneNo_et = (EditText) findViewById(R.id.editText_code);
		
		signUp_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	
				 // go to the tabs
		         Intent intent = new Intent(RegisterActivity.this, LandingPage.class);
		         RegisterActivity.this.startActivity(intent);
		         
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
