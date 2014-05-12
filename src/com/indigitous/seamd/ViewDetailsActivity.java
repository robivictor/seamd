package com.indigitous.seamd;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDetailsActivity extends Activity {

	String Description_String;
	
	TextView description_tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdetails);
		
		Description_String = getIntent().getStringExtra("description"); // get the rate from the previous activity
		
		description_tv = (TextView) findViewById(R.id.textView_detaileddescription);
		
		description_tv.setText(Description_String);
	}
}
